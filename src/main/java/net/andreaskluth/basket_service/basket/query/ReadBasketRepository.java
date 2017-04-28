package net.andreaskluth.basket_service.basket.query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadBasketRepository extends JpaRepository<ReadBasket, String> {

}
