package tech.jamersondev.lab_reactive.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import tech.jamersondev.lab_reactive.EventForm;
import tech.jamersondev.lab_reactive.interfaces.IEvent;
import tech.jamersondev.lab_reactive.repositorys.EventRepository;


@Service
public class EventService implements IEvent {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Flux<EventForm> listAll() {
        return eventRepository.findAll().map(EventForm::new);
    }
}
