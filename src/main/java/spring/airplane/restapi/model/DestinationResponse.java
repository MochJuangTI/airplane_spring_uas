package spring.airplane.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DestinationResponse {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private String photos;
    private Boolean insurance;
    private Boolean refundable;
    private Double vat;
    private CountryResponse country;
}
