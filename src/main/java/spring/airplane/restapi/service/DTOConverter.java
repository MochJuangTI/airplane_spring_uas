package spring.airplane.restapi.service;

import spring.airplane.restapi.model.*;
import spring.airplane.restapi.entity.*;

import java.util.stream.Collectors;

public class DTOConverter {

    public static BookingResponse toBookingResponse(Booking booking) {
        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setId(booking.getId());
        bookingResponse.setDate(booking.getDate());
        bookingResponse.setCustomer(toCustomerResponse(booking.getCustomer()));
        bookingResponse.setDestination(toDestinationResponse(booking.getDestination()));
        bookingResponse.setBookingItems(booking.getBookingBookingItems().stream()
                .map(DTOConverter::toBookingItemResponse)
                .collect(Collectors.toList()));
        bookingResponse.setInvoice(toInvoiceResponse(booking.getInvoice()));
        return bookingResponse;
    }

    public static CustomerResponse toCustomerResponse(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getFullname(), customer.getEmail(), customer.getHobby(), customer.getPhotos());
    }

    public static DestinationResponse toDestinationResponse(Destination destination) {
        return new DestinationResponse(destination.getId(), destination.getName(), destination.getDescription(), destination.getPrice(),
                destination.getPhotos(), destination.getInsurance(), destination.getRefundable(), destination.getVat(), toCountryResponse(destination.getCountry()));
    }

    public static BookingItemResponse toBookingItemResponse(BookingItem bookingItem) {
        return new BookingItemResponse(bookingItem.getId(), bookingItem.getSeat(), bookingItem.getTotalPrice());
    }

    public static InvoiceResponse toInvoiceResponse(Invoice invoice) {
        if (invoice == null) {
            return null;
        }
        return new InvoiceResponse(invoice.getId(), invoice.getTotalPayments(), invoice.getIsPaid(), invoice.getTotalVat(), null);
    }

    public static CountryResponse toCountryResponse(Country country) {
        if (country == null) {
            return null;
        }
        return new CountryResponse(country.getId(), country.getName());
    }

    public static CreditResponse toCreditResponse(Credit credit) {
        return new CreditResponse(credit.getId(), credit.getBalance(), toCustomerResponse(credit.getCustomer()));
    }

    public static CreditHistoryResponse toCreditHistoryResponse(CreditHistory creditHistory) {
        return new CreditHistoryResponse(creditHistory.getId(), creditHistory.getTotalPrice(), toCreditResponse(creditHistory.getCredit()), toInvoiceResponse(creditHistory.getInvoice()));
    }
}
