package com.example.cardisassemblyservice.templates;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MessageToCreateAdvertising {

    private String typeMessage;


    private String itemCar;

    private BigDecimal price;

    private String  itemState;


    private String photoPath;


    private String category;


    private String description;


    /*
     * Getters and Setters
     */


    public String getItemCar() {
        return itemCar;
    }

    public void setItemCar(String itemCar) {
        this.itemCar = itemCar;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getItemState() {
        return itemState;
    }

    public void setItemState(String itemState) {
        this.itemState = itemState;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(String typeMessage) {
        this.typeMessage = typeMessage;
    }
}
