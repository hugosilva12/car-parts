package com.example.cardisassemblyservice.templates;

import lombok.Data;



@Data
public class MessageToSend {

    private String carId;

    private String itemCarId;

    private String typeOperation;

    /*
     * Getters and Setters
     */

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getItemCarId() {
        return itemCarId;
    }

    public void setItemCarId(String itemCarId) {
        this.itemCarId = itemCarId;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }
}
