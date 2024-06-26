package spring.airplane.restapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.airplane.restapi.entity.*;
import spring.airplane.restapi.model.*;
import spring.airplane.restapi.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CreditRepository creditRepository;
    private final InvoiceRepository invoiceRepository;
    private final CreditHistoryRepository creditHistoryRepository;
    private final BookingItemRepository bookingItemRepository;
    private final DestinationRepository destinationRepository;
    private final CustomerRepository customerRepository;
    private final ValidationService validationService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, CreditRepository creditRepository,
                          InvoiceRepository invoiceRepository, CreditHistoryRepository creditHistoryRepository,
                          BookingItemRepository bookingItemRepository, DestinationRepository destinationRepository,
                          CustomerRepository customerRepository, ValidationService validationService) {
        this.bookingRepository = bookingRepository;
        this.creditRepository = creditRepository;
        this.invoiceRepository = invoiceRepository;
        this.creditHistoryRepository = creditHistoryRepository;
        this.bookingItemRepository = bookingItemRepository;
        this.destinationRepository = destinationRepository;
        this.customerRepository = customerRepository;
        this.validationService = validationService;
    }

    @Transactional
    public BookingResponse create(CreateBookingRequest request) {
        validationService.validate(request);

        if (request.getSeats() == null || request.getSeats().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seats list cannot be null or empty");
        }

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        Destination destination = destinationRepository.findById(request.getDestinationId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destination not found"));

        double totalPrice = destination.getPrice() * request.getSeats().size();
        Credit credit = customer.getCustomerCredits();
        if (credit.getBalance() < totalPrice) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Insufficient credit");
        }

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setDestination(destination);
        LocalDate date = LocalDate.parse(request.getDate());
        booking.setDate(date);
        bookingRepository.save(booking);

        List<BookingItem> bookingItems = new ArrayList<>();
        for (Integer seatNumber : request.getSeats()) {
            BookingItem bookingItem = new BookingItem();
            bookingItem.setSeat(seatNumber);
            bookingItem.setTotalPrice(destination.getPrice());
            bookingItem.setBooking(booking);
            bookingItemRepository.save(bookingItem);
            bookingItems.add(bookingItem);
        }

        Invoice invoice = new Invoice();
        invoice.setIsPaid(false);
        invoice.setTotalPayments(totalPrice);
        invoice.setTotalVat(destination.getVat() * request.getSeats().size());
        invoice.setBooking(booking);
        invoiceRepository.save(invoice);

        CreditHistory creditHistory = new CreditHistory();
        creditHistory.setCredit(credit);
        creditHistory.setTotalPrice(totalPrice);
        creditHistory.setInvoice(invoice);
        creditHistoryRepository.save(creditHistory);

        credit.setBalance(credit.getBalance() - totalPrice);
        creditRepository.save(credit);

        booking.setBookingBookingItems(bookingItems);
        invoice.setCreditHistory(creditHistory);
        booking.setInvoice(invoice);

        return DTOConverter.toBookingResponse(booking);
    }

    public BookingResponse getBookingById(Integer id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found"));
        return DTOConverter.toBookingResponse(booking);
    }

    // Method to get all bookings by customer ID
    public List<BookingResponse> getAllBookingsByCustomer(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        List<Booking> bookings = bookingRepository.findByCustomer(customer);
        return bookings.stream()
                .map(DTOConverter::toBookingResponse)
                .collect(Collectors.toList());
    }

    public BookingResponse payBooking(Integer id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found"));

        Invoice invoice = booking.getInvoice();
        if (invoice == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found for this booking");
        }

        invoice.setIsPaid(true);
        invoiceRepository.save(invoice);

        return DTOConverter.toBookingResponse(booking);
    }

    public CheckingAvailableSeatResponse checkingAvailableSeatResponse(CheckingAvailableSeatRequest request) {
        validationService.validate(request);

        Destination destination = destinationRepository.findById(request.getDestinationId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destination not found"));

        CheckingAvailableSeatResponse response = new CheckingAvailableSeatResponse();
        response.setDestination(DTOConverter.toDestinationResponse(destination));
        response.setDate(request.getDate());
        response.setSeats(getAvailableSeats(request.getDate(), destination));

        return response;
    }

    private List<Seat> getAvailableSeats(String date, Destination destination) {
        List<Integer> allSeatNumbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        List<Seat> seats = new ArrayList<>();

        for (Integer seatNumber : allSeatNumbers) {
            Seat seat = new Seat();
            seat.setNo(seatNumber);
            seat.setAvailable(true);
            seats.add(seat);
        }

        List<Booking> bookings = bookingRepository.findByDateAndDestination(LocalDate.parse(date), destination);
        for (Booking booking : bookings) {
            for (BookingItem item : booking.getBookingBookingItems()) {
                seats.stream()
                        .filter(seat -> seat.getNo().equals(item.getSeat()))
                        .forEach(seat -> seat.setAvailable(false));
            }
        }

        return seats;
    }
}
