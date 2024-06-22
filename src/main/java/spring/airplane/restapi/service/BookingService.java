package spring.airplane.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import spring.airplane.restapi.entity.*;
import spring.airplane.restapi.model.CheckingAvailableSeatRequest;
import spring.airplane.restapi.model.CheckingAvailableSeatResponse;
import spring.airplane.restapi.model.CreateBookingRequest;
import spring.airplane.restapi.model.Seat;
import spring.airplane.restapi.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private CreditRepository creditRepository;
    private InvoiceRepository invoiceRepository;
    private CreditHistoryRepository creditHistoryRepository;
    private BookingItemRepository bookingItemRepository;

    @Autowired
    public void setBookingItem(BookingItemRepository bookingItemRepository) {
        this.bookingItemRepository = bookingItemRepository;
    }

    @Autowired
    public void setCreditHistoryRepository(CreditHistoryRepository creditHistoryRepository) {
        this.creditHistoryRepository = creditHistoryRepository;
    }

    @Autowired
    public void setInvoiceRepository(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Autowired
    public void setBookingRepository(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    private DestinationRepository destinationRepository;
    @Autowired
    public void setDestinationRepository(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    private CustomerRepository customerRepository;
    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private ValidationService validationService;
    @Autowired
    public void setValidationService(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Transactional
    public Booking create(CreateBookingRequest request) {
        // TODO: validation
        validationService.validate(request);

        // TODO: checking customer exist
        Customer customer = customerRepository.findById(request.customer_id.toString())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer is not found"));

        // TODO : check destination exist
        Destination destination = destinationRepository.findById(request.destination_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destination is not found"));

        // TODO: checking balance
        Double totalPrice = destination.getPrice() * request.getSeat().size();
        Credit credit =  customer.getCustomerCredits();
        if (credit.getBalance() < destination.getPrice()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Credit is not enough");
        }

        // TODO: checking seat

        // TODO: create booking
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setDestination(destination);
        System.out.println(request.date);
        LocalDate date = LocalDate.parse(request.date);
        booking.setDate(date);
        bookingRepository.save(booking);

        List<BookingItem> bookingItems = new ArrayList<>();

        // TODO: create booking item
        for (Integer seatNumber : request.getSeat()) {
            BookingItem bookingItem = new BookingItem();
            bookingItem.setSeat(seatNumber);
            bookingItem.setTotalPrice(destination.getPrice());
            bookingItem.setBooking(booking);
            bookingItemRepository.save(bookingItem);

            bookingItems.add(bookingItem);
        }

        // TODO: create invoice
        Invoice invoice = new Invoice();
        invoice.setIsPaid(false);
        invoice.setTotalPayments(totalPrice);
        invoice.setTotalVat(destination.getVat() * request.getSeat().size());
        invoiceRepository.save(invoice);


        // TODO: create credit history
        CreditHistory creditHistory = new CreditHistory();
        creditHistory.setCredit(credit);
        creditHistory.setTotalPrice(totalPrice);
        creditHistory.setInvoice(invoice);
        creditHistoryRepository.save(creditHistory);


        // TODO: decrease balance
        credit.setBalance( credit.getBalance() - totalPrice );
        creditRepository.save(credit);

        // fill booking
        booking.setBookingBookingItems(bookingItems);
        invoice.setCreditHistory(creditHistory);
        booking.setInvoice(invoice);
        booking.setDestination(destination);

        return booking;
    }

    public CheckingAvailableSeatResponse checkingAvailableSeatResponse(CheckingAvailableSeatRequest request) {
        // TODO: validation
        validationService.validate(request);

        // TODO: checking destination exist
        Destination destination = destinationRepository.findById(request.destination_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destination is not found"));

        // TODO: checking seat in booking and booking item and destination
        CheckingAvailableSeatResponse availableSeatResponse = new CheckingAvailableSeatResponse();
        availableSeatResponse.destination = destination;
        availableSeatResponse.date = request.date;
        availableSeatResponse.seats = getAvailableSeat(request.date, destination);

        return availableSeatResponse;
    }

    private List<Seat> getAvailableSeat(String date, Destination destination) {
        List<Integer> availableSeat = List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
        List<Seat> listSeat = new ArrayList<>();

        for (Integer seatNumber : availableSeat) {
            Seat seat  = new Seat();
            seat.no = seatNumber;
            seat.isAvailable = false;
        }

        return listSeat;
    }

    public Booking payBooking(Integer id) {

        // TODO: checking booking exist
        // TODO: update invoice to paid
        return new Booking();
    }

    @Autowired
    public void setCreditRepository(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }
}

