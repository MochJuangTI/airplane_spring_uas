package spring.airplane.restapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckingAvailableSeatRequest {
    @Min(1)
    @JsonProperty("destination_id")
    private Integer destinationId;
    @NotBlank
    private String date;
}
