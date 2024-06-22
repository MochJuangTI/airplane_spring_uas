package spring.airplane.restapi.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrupdateDestinationRequest {
    @NotBlank
    public String name;
    @NotBlank
    public String description;
    @Min(1)
    public double price;
    @Min(1)
    public Integer country_id;
    @NotBlank
    public String photos;
    public boolean insurance;
    public boolean refundable;
    @Min(1)
    public double vat;
}
