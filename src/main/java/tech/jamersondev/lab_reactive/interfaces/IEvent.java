package tech.jamersondev.lab_reactive.interfaces;

import reactor.core.publisher.Flux;
import tech.jamersondev.lab_reactive.EventForm;
import tech.jamersondev.lab_reactive.model.Event;


public interface IEvent {
    Flux<EventForm> listAll();
}
