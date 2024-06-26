package spring.airplane.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import spring.airplane.restapi.model.CreateOrUpdateDestinationRequest;
import spring.airplane.restapi.model.DestinationResponse;
import spring.airplane.restapi.model.WebResponse;
import spring.airplane.restapi.service.DestinationService;

import java.util.List;

@RestController
@RequestMapping("/destination")
public class DestinationController {
    private final DestinationService destinationService;

    @Autowired
    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<DestinationResponse> create(@RequestBody CreateOrUpdateDestinationRequest request) {
        DestinationResponse destination = destinationService.create(request);
        return WebResponse.<DestinationResponse>builder().data(destination).build();
    }

    @PatchMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<DestinationResponse> update(@PathVariable("id") Integer id, @RequestBody CreateOrUpdateDestinationRequest request) {
        DestinationResponse destination = destinationService.update(id, request);
        return WebResponse.<DestinationResponse>builder().data(destination).build();
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<DestinationResponse> get(@PathVariable("id") Integer id) {
        DestinationResponse destination = destinationService.get(id);
        return WebResponse.<DestinationResponse>builder().data(destination).build();
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<DestinationResponse>> getAll() {
        List<DestinationResponse> destinations = destinationService.getAll();
        return WebResponse.<List<DestinationResponse>>builder().data(destinations).build();
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<DestinationResponse> delete(@PathVariable("id") Integer id) {
        DestinationResponse destination = destinationService.delete(id);
        return WebResponse.<DestinationResponse>builder().data(destination).build();
    }
}
