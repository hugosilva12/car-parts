package com.example.precariousservice.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CarSummaryReadDto {

    private String carId;

    private BigDecimal valueGenerated;

    /*
     * Getters and Setters
     */
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
