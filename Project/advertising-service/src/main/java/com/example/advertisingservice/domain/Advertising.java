package com.example.advertisingservice.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "advertising")
public class Advertising implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advertising_id", updatable = false, nullable = false)
    private Long id;


    @Column(name = "item_car", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    @NotNull
    private UUID itemCar;


    @Column(name = "price", nullable = false, precision = 10)
    @NotNull
    private BigDecimal price;


    @Column(name = "item_state", nullable = false, precision = 10)
    @NotNull
    private String  itemState;


    @Column(name = "photo_path", nullable = true, precision = 10)
    private String photoPath;


    @Column(name = "category", nullable = true, precision = 10)
    private String category;


    @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(500)")
    private String description;

    /*
     * Getters and Setters
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
