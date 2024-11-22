package com.example.salesservice.dtos.read;

import com.example.salesservice.dtos.global.BasicDto;
import com.example.salesservice.global.SaleState;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

public class SaleReadDto extends BasicDto {

    private UUID client;

    private UUID itemCar;

    private ZonedDateTime date;

    private BigDecimal price;

    private SaleState state;

    /*
     * Getters and Setters
     */

    public UUID getClient() {
        return client;
    }

    public void setClient(UUID client) {
        this.client = client;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public SaleState getState() {
        return state;
    }

    public void setState(SaleState state) {
        this.state = state;
    }

    public UUID getItemCar() {
        return itemCar;
    }

    public void setItemCar(UUID itemCar) {
        this.itemCar = itemCar;
    }
}


