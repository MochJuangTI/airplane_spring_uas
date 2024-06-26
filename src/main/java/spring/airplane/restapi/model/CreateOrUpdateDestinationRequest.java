package spring.airplane.restapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrUpdateDestinationRequest {
    @NotBlank
    public String name;
    @NotBlank
    public String description;
    @Min(1)
    public double price;
    @Min(1)
    @JsonProperty("country_id")
    public Integer countryId;
    @NotBlank
    public String photos;
    public Boolean insurance;
    public Boolean refundable;
    @Min(1)
    public double vat;
}
