package pl.konczak.microservices.domain;

import java.io.Serializable;

import pl.konczak.microservices.client.product.Product;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Item
        implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;

    private String name;

    private long quantity;

    private int unitPrice;

    protected Item() {
    }

    Item(Order order, Product product, long quantity) {
        this.order = order;
        this.name = product.getName();
        this.quantity = quantity;
        this.unitPrice = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getQuantity() {
        return quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

}
