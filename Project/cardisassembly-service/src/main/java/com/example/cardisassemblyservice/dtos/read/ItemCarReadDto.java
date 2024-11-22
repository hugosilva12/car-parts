package com.example.cardisassemblyservice.dtos.read;


import com.example.cardisassemblyservice.dtos.global.BasicDto;
import com.example.cardisassemblyservice.global.enums.ItemCarLifecycle;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

public class ItemCarReadDto extends BasicDto {


    private UUID carId;

    private UUID employeeId;

    private String itemSku;

    private BigDecimal price;

    private ItemCarLifecycle itemCarLifecycle;

    private String photoPath;

    private ItemStateReadDto itemState;

    private CategoryReadDto categoryReadDto;

    private ZonedDateTime date;
    private Object description;

    /*
     * Getters and Setters
     */

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

    public ItemStateReadDto getItemState() {
        return itemState;
    }

    public void setItemState(ItemStateReadDto itemState) {
        this.itemState = itemState;
    }

    public CategoryReadDto getCategoryReadDto() {
        return categoryReadDto;
    }

    public void setCategoryReadDto(CategoryReadDto categoryReadDto) {
        this.categoryReadDto = categoryReadDto;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }
}
