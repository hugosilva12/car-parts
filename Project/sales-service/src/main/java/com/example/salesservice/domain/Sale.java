package com.example.salesservice.domain;


import com.example.salesservice.global.SaleState;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "sale")
public class Sale  implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "sale_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;


    @Column(name = "client", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    @NotNull
    private UUID client;


    @Column(name = "item_car", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    @NotNull
    private UUID itemCar;

    @Column(name = "date", nullable = false, length = 19)
    @NotNull
    private ZonedDateTime date;


    @Column(name = "price", nullable = false, precision = 10)
    @NotNull
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", columnDefinition = "enum('CANCELED','CONFIRMED','AWAITS_CONFIRMATION')")
    private SaleState state;

    /*
     * Getters and Setters
     */

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
