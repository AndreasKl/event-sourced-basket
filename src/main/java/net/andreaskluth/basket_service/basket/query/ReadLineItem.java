package net.andreaskluth.basket_service.basket.query;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Entity
public class ReadLineItem {

    @Id
    private String id = Objects.toString(UUID.randomUUID());
    private String productId;
    private String productName;
    private int priceInCent;
    private int quantity;

    ReadLineItem() {
        // To please JPA.
    }

    public ReadLineItem(String productId, String productName, int priceInCent, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.priceInCent = priceInCent;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getPriceInCent() {
        return priceInCent;
    }

    public int getQuantity() {
        return quantity;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, JSON_STYLE);
    }

}
