package tech.jamersondev.lab_reactive.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import tech.jamersondev.lab_reactive.enums.EventTypeEnum;

import java.util.Date;

@Table(name = "event")
public class Event {

    @Id
    private Long id;
    private EventTypeEnum type;
    private String name;
    private Date createdDate;
    private String description;

    public Event(EventTypeEnum type, String name, Date createdDate, String description) {
        this.type = type;
        this.name = name;
        this.createdDate = createdDate;
        this.description = description;
    }

    public EventTypeEnum getType() {
        return type;
    }

    public void setType(EventTypeEnum type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
