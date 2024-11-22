package com.example.purchaseservice.domain;

import com.example.purchaseservice.global.enums.CarState;
import com.example.purchaseservice.global.enums.FuelType;
import com.example.purchaseservice.global.enums.GearType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "car_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @NotBlank(message = "Brand can't be blank or null")
    @Column(name = "brand")
    private String brand;

    @NotBlank(message = "Model can't be blank or null")
    @Column(name = "model")
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel")
    @NotNull
    private FuelType fuelType;

    @Column(name = "year")
    @NotNull
    private Integer year;

    @Column(name = "hp")
    @NotNull
    private Integer hp;

    @Column(name = "km")
    @NotNull
    private Integer km;

    @Column(name = "engine_capacity")
    @NotNull
    private Integer engine_capacity;

    @NotBlank(message = "Vin can't be blank or null")
    @Column(name = "vin", unique = true, columnDefinition = "VARCHAR(17)")
    private String vin;

    @Column(name = "price", nullable = false, precision = 10)
    @NotNull
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "gear")
    @NotNull
    private GearType gear;

    @Column(name = "entry_date", nullable = false, length = 19)
    @NotNull
    private ZonedDateTime entryDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "car_state")
    @NotNull
    private CarState carState;

    /*
     * Getters and Setters y
     */

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
