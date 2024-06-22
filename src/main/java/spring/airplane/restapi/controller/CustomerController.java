package spring.airplane.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import spring.airplane.restapi.entity.Customer;
import spring.airplane.restapi.model.LoginCustomerRequest;
import spring.airplane.restapi.model.RegisterCustomerRequest;
import spring.airplane.restapi.model.WebResponse;
import spring.airplane.restapi.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private CustomerService customerService;
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    @PutMapping(
            path = "/customer/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Customer> register(@RequestBody RegisterCustomerRequest request) {
        Customer customer = customerService.register(request);
        return WebResponse.<Customer>builder().data(customer).build();
    }

    @PostMapping(
            path = "/customer/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Customer> login(@RequestBody LoginCustomerRequest request) {
        Customer customer = customerService.login(request);
        return WebResponse.<Customer>builder().data(customer).build();
    }
}
