package tech.jamersondev.lab_reactive.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import tech.jamersondev.lab_reactive.EventForm;
import tech.jamersondev.lab_reactive.enums.EventTypeEnum;
import tech.jamersondev.lab_reactive.services.EventService;

import java.net.URI;
import java.time.Duration;
import java.util.Locale;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;
    private final Sinks.Many<EventForm> eventFormSink;

    public EventController(EventService eventService) {
        this.eventService = eventService;
        this.eventFormSink = Sinks.many().multicast().onBackpressureBuffer();
    }

    @GetMapping
    public Flux<EventForm> listAll(){
        return eventService.listAll();
    }

    @GetMapping("/{id}")
    public Mono<EventForm> findEventByIdentifier(@PathVariable("id") Long id){
        return eventService.findByIdentifier(id);
    }

    @PostMapping
    @Transactional
    public Mono<ResponseEntity<EventForm>> create(@RequestBody EventForm form, UriComponentsBuilder builder){
        return this.eventService.create(form).doOnSuccess(event -> eventFormSink.tryEmitNext(new EventForm(event))).map(events -> {
            URI uri = builder.path("/event/{id}").buildAndExpand(events.getId()).toUri();
            return ResponseEntity.created(uri).body(new EventForm(events));
        });
    }

    @DeleteMapping("/{id}")
    @Transactional
    public Mono<ResponseEntity<Void>> deleteEvent(@PathVariable("id") Long id){
        return eventService.deleteEvent(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

    @GetMapping(value = "/category/{type}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EventForm> getEventByType(@PathVariable("type") String type){
        return Flux.merge(this.eventService.findByType(EventTypeEnum.valueOf(type.toUpperCase())), eventFormSink.asFlux())
                .delayElements(Duration.ofSeconds(4));

    }
}
