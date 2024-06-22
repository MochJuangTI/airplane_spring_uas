package spring.airplane.restapi.service;

import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import spring.airplane.restapi.entity.Country;
import spring.airplane.restapi.entity.Destination;
import spring.airplane.restapi.model.CreateOrupdateDestinationRequest;
import spring.airplane.restapi.repository.CountryRepository;
import spring.airplane.restapi.repository.DestinationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class DestinationService {

    @Getter
    private CountryRepository countryRepository;
    @Autowired
    public void setCountryRepository(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Getter
    private DestinationRepository destinationRepository;
    @Autowired
    public void setDestinationRepository(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Getter
    private ValidationService validationService;
    @Autowired
    public void setValidationService(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Transactional
    public Destination create(CreateOrupdateDestinationRequest request) {
        // TODO: validation
        validationService.validate(request);

        // TODO: checking country exist
        Country country = countryRepository.findById(request.country_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country is not found"));

        // TODO : store to db
        Destination destination = new Destination();
        destination.setCountry(country);
        destination.setName(request.name);
        destination.setDescription(request.description);
        destination.setPrice(request.price);
        destination.setPhotos(request.photos);
        destination.setInsurance(request.insurance);
        destination.setRefundable(request.refundable);
        destination.setVat(request.vat);
        destinationRepository.save(destination);

        return destination;
    }

    @Transactional
    public Destination update(Integer id ,CreateOrupdateDestinationRequest request) {
        // TODO: validation
        validationService.validate(request);

        // TODO: checking country exist
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country is not found"));

        // TODO : check destination exist
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destination is not found"));

        // TODO : update to db
        destination.setCountry(country);
        destination.setName(request.name);
        destination.setDescription(request.description);
        destination.setPrice(request.price);
        destination.setPhotos(request.photos);
        destination.setInsurance(request.insurance);
        destination.setRefundable(request.refundable);
        destination.setVat(request.vat);
        destinationRepository.save(destination);
        return destination;
    }

    public Destination get(Integer id) {
        // TODO: get from db by id
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destination is not found"));
        return destination;
    }

    public List<Destination> getAll() {
        return destinationRepository.findAll();
    }

    @Transactional
    public Destination delete(Integer id) {
        // TODO: checking destination exist
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destination is not found"));

        // TODO: delete destination from db
        destinationRepository.delete(destination);
        return destination;
    }
}
