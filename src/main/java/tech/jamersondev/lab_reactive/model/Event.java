package tech.jamersondev.lab_reactive.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import tech.jamersondev.lab_reactive.enums.EventTypeEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "event")
public class Event {

    @Id
    private Long id;
    private EventTypeEnum type;
    private String name;
    @Column("createddate") //formatação da coluna, pois na persistência do objeto é adicionado _
    private LocalDate createdDate;
    private String description;
    private boolean deleted;

    public Event(EventTypeEnum type, String name, String description) {
        this.type = type;
        this.name = name;
        this.createdDate = LocalDate.now();
        this.description = description;
        this.deleted = false;
    }

    public Long getId() {
        return id;
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
