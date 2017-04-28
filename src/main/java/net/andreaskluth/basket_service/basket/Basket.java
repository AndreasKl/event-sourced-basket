package net.andreaskluth.basket_service.basket;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import net.andreaskluth.basket_service.basket.events.BasketCreatedEvent;
import net.andreaskluth.basket_service.basket.events.LineItemAddedEvent;

@Aggregate
public class Basket {

    @AggregateIdentifier
    private String id;
    private final List<LineItem> lineItems = new ArrayList<>();

    @SuppressWarnings("unused")
    private Basket() {
    }

    public Basket(String id) {
        apply(new BasketCreatedEvent(id));
    }
    
    public void addLineItem(String productId, String productName, int priceInCent, int quantity){
        if(quantity <= 0){
            // ?? What is the proper pattern ??
            throw new RuntimeException();
        }
        lineItems.add(new LineItem(productId, productName, priceInCent, quantity));
        apply(new LineItemAddedEvent(id, productId, productName, priceInCent, quantity));
    }

    @EventHandler
    public void on(BasketCreatedEvent event) {
        this.id = event.id();
    }

    @EventHandler
    public void on(LineItemAddedEvent event) {
        lineItems.add(new LineItem(event.getProductId(), event.getProductName(), event.getPriceInCent(),
                event.getQuantity()));
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}