package spring.airplane.restapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.airplane.restapi.entity.Country;
import spring.airplane.restapi.entity.Destination;
import spring.airplane.restapi.model.CreateOrUpdateDestinationRequest;
import spring.airplane.restapi.model.DestinationResponse;
import spring.airplane.restapi.repository.CountryRepository;
import spring.airplane.restapi.repository.DestinationRepository;
import spring.airplane.restapi.service.DTOConverter;
import spring.airplane.restapi.service.ValidationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinationService {

    private final CountryRepository countryRepository;
    private final DestinationRepository destinationRepository;
    private final ValidationService validationService;

    @Autowired
    public DestinationService(CountryRepository countryRepository, DestinationRepository destinationRepository, ValidationService validationService) {
        this.countryRepository = countryRepository;
        this.destinationRepository = destinationRepository;
        this.validationService = validationService;
    }

    @Transactional
    public DestinationResponse create(CreateOrUpdateDestinationRequest request) {
        validationService.validate(request);

        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country is not found"));

        Destination destination = new Destination();
        destination.setCountry(country);
        destination.setName(request.getName());
        destination.setDescription(request.getDescription());
        destination.setPrice(request.getPrice());
        destination.setPhotos(request.getPhotos());
        destination.setInsurance(request.getInsurance());
        destination.setRefundable(request.getRefundable());
        destination.setVat(request.getVat());
        destinationRepository.save(destination);

        return DTOConverter.toDestinationResponse(destination);
    }

    @Transactional
    public DestinationResponse update(Integer id, CreateOrUpdateDestinationRequest request) {
        validationService.validate(request);

        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country is not found"));

        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destination is not found"));

        destination.setCountry(country);
        destination.setName(request.getName());
        destination.setDescription(request.getDescription());
        destination.setPrice(request.getPrice());
        destination.setPhotos(request.getPhotos());
        destination.setInsurance(request.getInsurance());
        destination.setRefundable(request.getRefundable());
        destination.setVat(request.getVat());
        destinationRepository.save(destination);

        return DTOConverter.toDestinationResponse(destination);
    }

    public DestinationResponse get(Integer id) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destination is not found"));
        return DTOConverter.toDestinationResponse(destination);
    }

    public List<DestinationResponse> getAll() {
        return destinationRepository.findAll().stream()
                .map(DTOConverter::toDestinationResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public DestinationResponse delete(Integer id) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destination is not found"));

        destinationRepository.delete(destination);
        return DTOConverter.toDestinationResponse(destination);
    }
}
