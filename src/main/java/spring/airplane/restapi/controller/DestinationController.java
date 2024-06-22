package spring.airplane.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import spring.airplane.restapi.entity.Destination;
import spring.airplane.restapi.model.CreateOrupdateDestinationRequest;
import spring.airplane.restapi.model.WebResponse;
import spring.airplane.restapi.service.DestinationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DestinationController {
    private DestinationService destinationService;

    @Autowired
    public void setDestinationService(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @PutMapping(
            path = "/destination",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Destination> create(@RequestBody CreateOrupdateDestinationRequest request) {
        Destination destination = destinationService.create(request);
        return WebResponse.<Destination>builder().data(destination).build();
    }

    @PatchMapping(
            path = "/destination/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Destination> update(@PathVariable("id") Integer id, @RequestBody CreateOrupdateDestinationRequest request) {
        Destination destination = destinationService.update(id, request);
        return WebResponse.<Destination>builder().data(destination).build();
    }

    @GetMapping(
            path = "/destination/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Destination> get(@PathVariable("id") Integer id) {
        Destination destination = destinationService.get(id);
        return WebResponse.<Destination>builder().data(destination).build();
    }

    @GetMapping(
            path = "/destination",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<Destination>> GetListDestination() {
        List<Destination> destinations = destinationService.getAll();
        return WebResponse.<List<Destination>>builder().data(destinations).build();
    }

    @DeleteMapping(
            path = "/destination/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Destination> delete(@PathVariable("id") Integer id) {
        Destination destination = destinationService.delete(id);
        return WebResponse.<Destination>builder().data(destination).build();
    }
}
