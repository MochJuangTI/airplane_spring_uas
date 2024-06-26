package spring.airplane.restapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.airplane.restapi.entity.Credit;
import spring.airplane.restapi.entity.Customer;
import spring.airplane.restapi.model.CustomerResponse;
import spring.airplane.restapi.model.LoginCustomerRequest;
import spring.airplane.restapi.model.RegisterCustomerRequest;
import spring.airplane.restapi.repository.CreditRepository;
import spring.airplane.restapi.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CreditRepository creditRepository;
    private final ValidationService validationService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CreditRepository creditRepository, ValidationService validationService) {
        this.customerRepository = customerRepository;
        this.creditRepository = creditRepository;
        this.validationService = validationService;
    }

    @Transactional
    public CustomerResponse register(RegisterCustomerRequest request) {
        validationService.validate(request);

        Customer customer = new Customer();
        customer.setEmail(request.getEmail());
        customer.setPassword(request.getPassword());
        customer.setFullname(request.getFullname());
        customer.setHobby(request.getHobby());
        customerRepository.save(customer);

        Credit credit = new Credit();
        credit.setCustomer(customer);
        credit.setBalance(100000.0);
        creditRepository.save(credit);

        return DTOConverter.toCustomerResponse(customer);
    }

    public CustomerResponse login(LoginCustomerRequest request) {
        validationService.validate(request);

        Customer customer = customerRepository.findFirstByEmailAndAndPassword(request.getEmail(), request.getPassword())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email or password is incorrect"));

        return DTOConverter.toCustomerResponse(customer);
    }
}
