package spring.airplane.restapi.model;

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
    public Integer destination_id;
    @Min(1)
    public Integer customer_id;
    @NotBlank
    public String date;
    public List<Integer> seat;
}
