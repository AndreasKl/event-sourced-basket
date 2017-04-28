package net.andreaskluth.basket_service.basket.commands;

import java.util.UUID;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class CreateBasketCommand {

    @TargetAggregateIdentifier
    private final String id;

    public CreateBasketCommand() {
        id = UUID.randomUUID().toString();
    }

    public String id() {
        return id;
    }

}
