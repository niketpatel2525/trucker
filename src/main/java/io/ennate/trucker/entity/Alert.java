package io.ennate.trucker.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Alert {
    @Id
    private String id;
    private String message;
    private Priority priority;

    @ManyToOne(cascade = CascadeType.ALL)
    private Vehicle vehicle;

    @CreationTimestamp
    private LocalDateTime timestamp;

    public Alert() {
        this.id = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
    }

    public Alert(String message, Priority priority, Vehicle vehicle) {
        this.id = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.priority = priority;
        this.vehicle = vehicle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
