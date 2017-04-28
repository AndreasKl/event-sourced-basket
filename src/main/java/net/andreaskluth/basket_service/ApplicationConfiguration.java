package net.andreaskluth.basket_service;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.eventhandling.saga.repository.SagaStore;
import org.axonframework.eventhandling.saga.repository.inmemory.InMemorySagaStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.andreaskluth.basket_service.basket.Basket;
import net.andreaskluth.basket_service.basket.commands.BasketCommandHandler;

@Configuration
public class ApplicationConfiguration {

    @Autowired
    private AxonConfiguration axonConfiguration;

    @Bean
    public BasketCommandHandler commandHandler(EventStore eventStore) {
        return new BasketCommandHandler(axonConfiguration.repository(Basket.class));
    }

    @Bean
    public CommandBus simpleCommandBus() {
        SimpleCommandBus simpleCommandBus = new SimpleCommandBus();
        simpleCommandBus.registerDispatchInterceptor(new BeanValidationInterceptor<>());
        return simpleCommandBus;
    }

    @Bean
    public EventStorageEngine eventStorageEngine() {
        return new InMemoryEventStorageEngine();
    }

    @Bean
    public SagaStore<Object> sagaStore() {
        return new InMemorySagaStore();
    }

}
