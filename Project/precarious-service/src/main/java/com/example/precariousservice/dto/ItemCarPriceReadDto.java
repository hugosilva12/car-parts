package com.example.precariousservice.dto;

import com.example.precariousservice.domain.Category;
import com.example.precariousservice.domain.ItemCarPrice;
import com.example.precariousservice.domain.ItemCarState;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class ItemCarPriceReadDto {

    private Long id;

    private BigDecimal price;

    private BigDecimal marketPrice;


    private ItemStateReadDto itemState;


    private CategoryReadDto category;


    private Object description;


    private ZonedDateTime saleDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public ItemStateReadDto getItemState() {
        return itemState;
    }

    public void setItemState(ItemStateReadDto itemState) {
        this.itemState = itemState;
    }

    public CategoryReadDto getCategory() {
        return category;
    }

    public void setCategory(CategoryReadDto category) {
        this.category = category;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public ZonedDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(ZonedDateTime saleDate) {
        this.saleDate = saleDate;
    }
}
