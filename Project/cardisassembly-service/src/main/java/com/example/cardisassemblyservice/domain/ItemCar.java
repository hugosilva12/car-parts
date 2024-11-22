package com.example.cardisassemblyservice.domain;


import com.example.cardisassemblyservice.global.enums.ItemCarLifecycle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * pom xml -> maven  generate sources for create  Qclasses
 */

@Entity
@Table(name = "item_car")
public class ItemCar implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "item_car_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "car_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    @NotNull
    private UUID carId;

    @Column(name = "employee_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    @NotNull
    private UUID employeeId;

    @Column(name = "item_sku")
    @NotNull
    private String itemSku;


    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    @NotNull
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_car_lifecycle", nullable = false)
    @NotNull
    private ItemCarLifecycle itemCarLifecycle;

    @Column(name = "photo_path", nullable = true)
    private String photoPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_state_id", nullable = false, foreignKey = @ForeignKey(name = "FK_Item_State"))
    private ItemCarState itemState;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "FK_Item_Category"))
    private Category category;


    @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(500)")
    private String description;


    @Column(name = "date", nullable = false, length = 19)
    @NotNull
    private ZonedDateTime date;

    /*
     * Getters and Setters
     */

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCarId() {
        return carId;
    }

    public void setCarId(UUID carId) {
        this.carId = carId;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public String getItemSku() {
        return itemSku;
    }

    public void setItemSku(String itemSku) {
        this.itemSku = itemSku;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public com.example.cardisassemblyservice.global.enums.ItemCarLifecycle getItemCarLifecycle() {
        return itemCarLifecycle;
    }

    public void setItemCarLifecycle(com.example.cardisassemblyservice.global.enums.ItemCarLifecycle itemCarLifecycle) {
        this.itemCarLifecycle = itemCarLifecycle;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public ItemCarState getItemState() {
        return itemState;
    }

    public void setItemState(ItemCarState itemState) {
        this.itemState = itemState;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }
}
