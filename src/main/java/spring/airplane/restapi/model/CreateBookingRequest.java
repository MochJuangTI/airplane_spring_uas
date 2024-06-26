package spring.airplane.restapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBookingRequest {
    @Min(1)
    @JsonProperty("destination_id")
    private Integer destinationId;

    @Min(1)
    @JsonProperty("customer_id")
    private Integer customerId;

    @NotBlank
    private String date;

    private List<Integer> seats;
}
