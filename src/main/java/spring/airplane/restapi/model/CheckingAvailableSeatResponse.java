package spring.airplane.restapi.model;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.airplane.restapi.entity.Destination;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckingAvailableSeatResponse {
    @NotBlank
    private DestinationResponse destination;
    @NotBlank
    private String date;
    @NotBlank
    private List<Seat> seats;
}

