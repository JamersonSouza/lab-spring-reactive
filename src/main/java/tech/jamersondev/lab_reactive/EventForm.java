package tech.jamersondev.lab_reactive;

import tech.jamersondev.lab_reactive.enums.EventTypeEnum;
import tech.jamersondev.lab_reactive.model.Event;

public record EventForm(String nome, String description, EventTypeEnum typeEnum) {
    public EventForm (Event event){
        this(event.getNome(), event.getDescription(), event.getType());
    }
}
