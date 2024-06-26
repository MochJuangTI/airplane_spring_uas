package spring.airplane.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingItemResponse {
    private Integer id;
    private Integer seat;
    private Double totalPrice;
}
