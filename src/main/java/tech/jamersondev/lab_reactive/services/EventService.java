package tech.jamersondev.lab_reactive.services;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jamersondev.lab_reactive.EventForm;
import tech.jamersondev.lab_reactive.interfaces.IEvent;
import tech.jamersondev.lab_reactive.model.Event;
import tech.jamersondev.lab_reactive.repositorys.EventRepository;



@Service
public class EventService implements IEvent {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Flux<EventForm> listAll() {
        return eventRepository.findAll()
                .filter(event -> !event.isDeleted()).map(EventForm::new);
    }

    @Override
    public Mono<EventForm> findByIdentifier(Long id) {
        return this.eventRepository.findById(id).filter(event -> !event.isDeleted())
                .switchIfEmpty(
                Mono.error(new ResponseStatusException(
                        HttpStatusCode.valueOf(404))))
                .map(EventForm::new);
    }

    @Override
    public Mono<Event> create(EventForm form) {
       Event event = new Event(form.type(), form.name(), form.description());
       return this.eventRepository.save(event);
    }

    @Override
    public Mono<Void> deleteEvent(Long id) {
     return this.eventRepository.findById(id).filter(event -> !event.isDeleted())
             .switchIfEmpty(
                     Mono.error(new ResponseStatusException(
                             HttpStatusCode.valueOf(404))))
              .flatMap(eve -> {
                  eve.setDeleted(true);
                  return eventRepository.save(eve);
              }).then();

    }
}
