package net.andreaskluth.basket_service.basket.commands;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;

import net.andreaskluth.basket_service.basket.Basket;

public class BasketCommandHandler {

    private Repository<Basket> repository;

    public BasketCommandHandler(Repository<Basket> basketRepository) {
        this.repository = basketRepository;
    }

    @CommandHandler
    public void handle(CreateBasketCommand command) throws Exception {
        repository.newInstance(() -> new Basket(command.id()));
    }

    @CommandHandler
    public void handle(AddLineItemCommand command) throws Exception {
        Aggregate<Basket> load = repository.load(command.id());
        load.execute(b -> b.addLineItem(command.getProductId(), command.getProductName(), command.getPriceInCent(),
                command.getQuantity()));
    }
}
