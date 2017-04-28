package net.andreaskluth.basket_service.basket.query;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.andreaskluth.basket_service.basket.events.BasketCreatedEvent;
import net.andreaskluth.basket_service.basket.events.LineItemAddedEvent;

@Component
public class BasketEventListener {

    private ReadBasketRepository basketRepository;

    @Autowired
    public BasketEventListener(ReadBasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @EventHandler
    public void on(BasketCreatedEvent event) {
        basketRepository.save(new ReadBasket(event.id()));
    }

    @EventHandler
    public void on(LineItemAddedEvent event) {
        basketRepository.findOne(event.id()).map(b -> addLineItem(event, b)).map(basketRepository::save);
    }

    private ReadBasket addLineItem(LineItemAddedEvent event, ReadBasket basket) {
        basket.addLineItem(new ReadLineItem(event.getProductId(), event.getProductName(), event.getPriceInCent(),
                event.getQuantity()));
        return basket;
    }
}
