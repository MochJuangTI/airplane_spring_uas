package spring.airplane.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.airplane.restapi.model.BookingResponse;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceResponse {
    private Integer id;
    private Double totalPayments;
    private Boolean isPaid;
    private Double totalVat;
    private BookingResponse booking;
}
