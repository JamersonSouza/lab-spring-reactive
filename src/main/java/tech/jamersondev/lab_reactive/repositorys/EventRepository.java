package tech.jamersondev.lab_reactive.repositorys;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import tech.jamersondev.lab_reactive.enums.EventTypeEnum;
import tech.jamersondev.lab_reactive.model.Event;

public interface EventRepository extends ReactiveCrudRepository<Event, Long> {
    Flux<Event> findByType(EventTypeEnum type);
}
