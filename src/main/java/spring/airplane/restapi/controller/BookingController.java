package spring.airplane.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import spring.airplane.restapi.entity.Booking;
import spring.airplane.restapi.model.*;
import spring.airplane.restapi.service.BookingService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {
    private BookingService bookingService;
    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PutMapping(
            path = "/booking",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Booking> create(@RequestBody CreateBookingRequest request) {
        Booking booking = bookingService.create(request);
        return WebResponse.<Booking>builder().data(booking).build();
    }

    @PostMapping(
            path = "/booking/:id/pay",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Booking> payBooking(@PathVariable("id") Integer id) {
        Booking booking = bookingService.payBooking(id);
        return WebResponse.<Booking>builder().data(booking).build();
    }

    @PostMapping(
            path = "/booking/seat",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<CheckingAvailableSeatResponse> create(@RequestBody CheckingAvailableSeatRequest request) {
        CheckingAvailableSeatResponse response = bookingService.checkingAvailableSeatResponse(request);
        return WebResponse.<CheckingAvailableSeatResponse>builder().data(response).build();
    }

}
