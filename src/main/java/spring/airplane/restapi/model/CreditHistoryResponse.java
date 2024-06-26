package spring.airplane.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditHistoryResponse {
    private Integer id;
    private Double totalPrice;
    private CreditResponse credit;
    private InvoiceResponse invoice;
}
