package tech.jamersondev.lab_reactive.repositorys;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import tech.jamersondev.lab_reactive.model.Event;

public interface EventRepository extends ReactiveCrudRepository<Event, Long> {
}
