package com.example.cardisassemblyservice.templates;

import lombok.Data;

@Data
public class MessageToSendSalesService {

    private String sender;

    private String saleId;


    private String typeOperation;

    /*
     * Getters and Setters
     */

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }
}
