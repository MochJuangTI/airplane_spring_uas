package spring.airplane.restapi.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Seat {
    @Min(1)
    public Number no;
    @NotBlank
    public Boolean isAvailable ;
}
