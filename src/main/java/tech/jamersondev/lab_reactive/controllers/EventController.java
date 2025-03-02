package tech.jamersondev.lab_reactive.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jamersondev.lab_reactive.EventForm;
import tech.jamersondev.lab_reactive.services.EventService;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public Flux<EventForm> listAll(){
        return eventService.listAll();
    }

    @GetMapping("{id}")
    public Mono<EventForm> findEventByIdentifier(@PathVariable("id") Long id){
        return eventService.findByIdentifier(id);
    }

}
