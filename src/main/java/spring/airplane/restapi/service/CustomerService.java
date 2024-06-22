package spring.airplane.restapi.service;

import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import spring.airplane.restapi.entity.Credit;
import spring.airplane.restapi.entity.Customer;
import spring.airplane.restapi.model.LoginCustomerRequest;
import spring.airplane.restapi.model.RegisterCustomerRequest;
import spring.airplane.restapi.repository.CreditRepository;
import spring.airplane.restapi.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class CustomerService {

    @Getter
    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Getter
    private CreditRepository creditRepository;

    @Autowired
    public void setCreditRepository(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Getter
    private ValidationService validationService;

    @Autowired
    public void setValidationService(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Transactional
    public Customer register(RegisterCustomerRequest request) {
        // TODO : validation
        validationService.validate(request);

        // TODO : filled customer data
        Customer customer = new Customer();
        customer.setEmail(request.email);
        customer.setPassword(request.password);
        customer.setFullname(request.fullname);
        customer.setHobby(request.hobby);
        customerRepository.save(customer);

        // TODO : create credit by customer
        Credit credit = new Credit();
        credit.setCustomer(customer);
        credit.setBalance(100000.0);
        creditRepository.save(credit);

        return customer;
    }

    public Customer login(LoginCustomerRequest request) {
        // TODO : validation
        validationService.validate(request);

        // TODO : checkin data to db
        Customer customer = customerRepository.findFirstByEmailAndAndPassword(request.email, request.password)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email or password is incorrect"));

        return customer;
    }
}
