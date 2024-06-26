package spring.airplane.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import spring.airplane.restapi.model.*;
import spring.airplane.restapi.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PutMapping(
            path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<CustomerResponse> register(@RequestBody RegisterCustomerRequest request) {
        CustomerResponse customer = customerService.register(request);
        return WebResponse.<CustomerResponse>builder().data(customer).build();
    }

    @PostMapping(
            path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<CustomerResponse> login(@RequestBody LoginCustomerRequest request) {
        CustomerResponse customer = customerService.login(request);
        return WebResponse.<CustomerResponse>builder().data(customer).build();
    }
}
