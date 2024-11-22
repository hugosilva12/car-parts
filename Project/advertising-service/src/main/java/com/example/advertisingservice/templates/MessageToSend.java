package com.example.advertisingservice.templates;

import lombok.Data;

@Data
public class MessageToSend {

    private String itemCarId;

    private String typeResponse;

    private String sender;

    /*
     * Getters and Setters
     */

    public String getItemCarId() {
        return itemCarId;
    }

    public void setItemCarId(String itemCarId) {
        this.itemCarId = itemCarId;
    }

    public String getTypeResponse() {
        return typeResponse;
    }

    public void setTypeResponse(String typeResponse) {
        this.typeResponse = typeResponse;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
