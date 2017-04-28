package net.andreaskluth.basket_service.basket.events;

public class BasketCreatedEvent {

    private final String id;

    public BasketCreatedEvent(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

}
