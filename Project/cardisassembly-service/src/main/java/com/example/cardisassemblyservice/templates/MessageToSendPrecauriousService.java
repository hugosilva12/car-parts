package com.example.cardisassemblyservice.templates;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class MessageToSendPrecauriousService {

    private String description;

    private BigDecimal price;


    private Long categoryId;

    private Long itemStateId;

    private String typeMessage;

    private String carId;

    /*
     * Getters and Setters
     */

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getItemStateId() {
        return itemStateId;
    }

    public void setItemStateId(Long itemStateId) {
        this.itemStateId = itemStateId;
    }

    public String getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(String typeMessage) {
        this.typeMessage = typeMessage;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }
}
