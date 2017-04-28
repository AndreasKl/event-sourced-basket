package net.andreaskluth.basket_service.basket;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class LineItem {

    private String productId;
    private String productName;
    private int priceInCent;
    private int quantity;

    public LineItem(String productId, String productName, int priceInCent, int quantity) {
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
        return ReflectionToStringBuilder.toString(this);
    }
}
