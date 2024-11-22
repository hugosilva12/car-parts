package com.example.salesservice.templates;

public class MessageSend {


    private String sender;

    private String itemCarId;

    private String operationType;


    private String idSale;

    /*
     * Getters and Setters
     */


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getItemCarId() {
        return itemCarId;
    }

    public void setItemCarId(String itemCarId) {
        this.itemCarId = itemCarId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getIdSale() {
        return idSale;
    }

    public void setIdSale(String idSale) {
        this.idSale = idSale;
    }
}
