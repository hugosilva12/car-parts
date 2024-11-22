package com.example.advertisingservice.dtos.read;


import com.example.advertisingservice.dtos.global.BasicDto;

import java.math.BigDecimal;
import java.util.UUID;

public class AdvertisingReadDto extends BasicDto {


    private UUID itemCar;


    private BigDecimal price;

    private String  itemState;


    private String photoPath;


    private String category;

    private Object description;

    /*
     * Getters and Setters
     */

    public UUID getItemCar() {
        return itemCar;
    }

    public void setItemCar(UUID itemCar) {
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


    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }
}
