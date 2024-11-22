package com.example.precariousservice.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Table(name = "car_summary")
public class CarSummary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_summary_id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "car_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @NotNull
    private String carId;

    @NotNull
    @Column(name = "value_generated", nullable = false, precision = 10, scale = 2)
    private BigDecimal valueGenerated;

    /*
     * Getters and Setters
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public BigDecimal getValueGenerated() {
        return valueGenerated;
    }

    public void setValueGenerated(BigDecimal valueGenerated) {
        this.valueGenerated = valueGenerated;
    }
}