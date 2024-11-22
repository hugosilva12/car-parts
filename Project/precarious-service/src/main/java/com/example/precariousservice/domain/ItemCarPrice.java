package com.example.precariousservice.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;


@Entity
@Table(name = "item_car_price")
public class ItemCarPrice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_car_price_id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "price", updatable = false,nullable = false, precision = 10, scale = 2)
    @NotNull
    private BigDecimal price;

    @Column(name = "market_Price", updatable = false,nullable = false, precision = 10, scale = 2)
    @NotNull
    private BigDecimal marketPrice;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_state_id", nullable = false, foreignKey = @ForeignKey(name = "FK_Item_State"))
    private ItemCarState itemState;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "FK_Item_Category"))
    private Category category;


    @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(500)")
    private String description;


    @Column(name = "sale_date", nullable = false, length = 19)
    @NotNull
    private ZonedDateTime saleDate;

    /*
     * Getters and Setters
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ZonedDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(ZonedDateTime saleDate) {
        this.saleDate = saleDate;
    }
}
