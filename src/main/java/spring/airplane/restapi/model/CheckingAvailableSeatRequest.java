package spring.airplane.restapi.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class CheckingAvailableSeatRequest {
    @Min(1)
    public Integer destination_id;
    @NotBlank
    public String date;
}
