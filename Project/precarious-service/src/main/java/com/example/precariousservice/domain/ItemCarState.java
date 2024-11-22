package com.example.precariousservice.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "item_car_state")
public class ItemCarState implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_state_id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "percentage", updatable = false, unique = true, nullable = false)
    private Long percentage;

    @Column(name = "description", updatable = false, unique = true, nullable = false)
    private String description;

    /*
     * Getters and Setters
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPercentage() {
        return percentage;
    }

    public void setPercentage(Long percentage) {
        this.percentage = percentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
