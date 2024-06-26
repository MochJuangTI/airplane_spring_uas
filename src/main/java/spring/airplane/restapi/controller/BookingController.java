package spring.airplane.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import spring.airplane.restapi.model.*;
import spring.airplane.restapi.service.BookingService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<BookingResponse> create(@RequestBody CreateBookingRequest request) {
        BookingResponse booking = bookingService.create(request);
        return WebResponse.<BookingResponse>builder().data(booking).build();
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<BookingResponse> getBookingById(@PathVariable("id") Integer id) {
        BookingResponse booking = bookingService.getBookingById(id);
        return WebResponse.<BookingResponse>builder().data(booking).build();
    }

    // Endpoint to get all bookings by customer ID
    @GetMapping(
            path = "/customer/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<BookingResponse>> getAllBookingsByCustomer(@PathVariable("customerId") Integer customerId) {
        List<BookingResponse> bookings = bookingService.getAllBookingsByCustomer(customerId);
        return WebResponse.<List<BookingResponse>>builder().data(bookings).build();
    }

    @PostMapping(
            path = "/{id}/pay",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<BookingResponse> payBooking(@PathVariable("id") Integer id) {
        BookingResponse booking = bookingService.payBooking(id);
        return WebResponse.<BookingResponse>builder().data(booking).build();
    }

    @PostMapping(
            path = "/seat",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<CheckingAvailableSeatResponse> checkAvailableSeat(@RequestBody CheckingAvailableSeatRequest request) {
        CheckingAvailableSeatResponse response = bookingService.checkingAvailableSeatResponse(request);
        return WebResponse.<CheckingAvailableSeatResponse>builder().data(response).build();
    }
}
