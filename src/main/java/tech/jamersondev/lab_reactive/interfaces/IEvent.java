package tech.jamersondev.lab_reactive.interfaces;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jamersondev.lab_reactive.EventForm;
import tech.jamersondev.lab_reactive.model.Event;


public interface IEvent {
    Flux<EventForm> listAll();

    Mono<EventForm> findByIdentifier(Long id);

    Mono<Event> create(EventForm form);
}
