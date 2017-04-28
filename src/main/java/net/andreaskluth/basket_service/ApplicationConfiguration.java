package net.andreaskluth.basket_service;

import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.andreaskluth.basket_service.basket.Basket;
import net.andreaskluth.basket_service.basket.commands.BasketCommandHandler;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public BasketCommandHandler commandHandler(AxonConfiguration axonConfiguration) {
        return new BasketCommandHandler(axonConfiguration.repository(Basket.class));
    }

}
