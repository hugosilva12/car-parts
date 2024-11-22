package com.example.purchaseservice.dtos.write;


import com.example.purchaseservice.dtos.global.BasicDto;
import com.example.purchaseservice.global.enums.CarState;
import com.example.purchaseservice.global.enums.FuelType;
import com.example.purchaseservice.global.enums.GearType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class CarWriteDto extends BasicDto {

    private String brand;

    private String model;

    private FuelType fuelType;

    private Integer year;

    private Integer hp;

    private Integer km;

    private Integer engine_capacity;

    private String vin;


    private BigDecimal price;


    private GearType gear;

    private ZonedDateTime entryDate;

    private CarState carState;

    /*
     * Getters and Setters
     */

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }

    public Integer getEngine_capacity() {
        return engine_capacity;
    }

    public void setEngine_capacity(Integer engine_capacity) {
        this.engine_capacity = engine_capacity;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public GearType getGear() {
        return gear;
    }

    public void setGear(GearType gear) {
        this.gear = gear;
    }

    public ZonedDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(ZonedDateTime entryDate) {
        this.entryDate = entryDate;
    }


    public CarState getCarState() {
        return carState;
    }

    public void setCarState(CarState carState) {
        this.carState = carState;
    }
}
