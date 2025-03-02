package tech.jamersondev.lab_reactive;

import tech.jamersondev.lab_reactive.enums.EventTypeEnum;
import tech.jamersondev.lab_reactive.model.Event;

public record EventForm(String name, String description, EventTypeEnum type) {
        public EventForm (Event event){
            this(event.getName(), event.getDescription(), event.getType());
        }
}
