package net.andreaskluth.basket_service.basket.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class AddLineItemCommand {

    @TargetAggregateIdentifier
    private final String id;
    private final String productId;
    private final String productName;
    private final int priceInCent;
    private final int quantity;

    public AddLineItemCommand(String id, String productId, String productName, int priceInCent, int quantity) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.priceInCent = priceInCent;
        this.quantity = quantity;
    }

    public String id() {
        return id;
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
}
