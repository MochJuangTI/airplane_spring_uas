package spring.airplane.restapi.model;

import jakarta.validation.constraints.Min;
import spring.airplane.restapi.entity.Destination;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;

public class CheckingAvailableSeatResponse {
    @NotBlank
    public Destination destination;
    @NotBlank
    public String date;
    @NotBlank
    public List<Seat> seats;
}

