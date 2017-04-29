package net.andreaskluth.basket_service.basket.query;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Entity
public class ReadBasket {

    @Id
    public String id;

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, orphanRemoval = true)
    public Set<ReadLineItem> lineItems = new HashSet<>();

    ReadBasket() {
        // Used by JPA.
    }

    public ReadBasket(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void addLineItem(ReadLineItem lineItem) {
        lineItems.add(lineItem);
    }

    public Set<ReadLineItem> getLineItems() {
        return lineItems;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, JSON_STYLE);
    }

}
