package spring.airplane.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
    private Integer id;
    private LocalDate date;
    private CustomerResponse customer;
    private DestinationResponse destination;
    private List<BookingItemResponse> bookingItems;
    private InvoiceResponse invoice;
}
