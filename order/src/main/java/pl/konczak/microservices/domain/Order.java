package pl.konczak.microservices.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import pl.konczak.microservices.client.product.Product;
import pl.konczak.microservices.client.user.User;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity()
@Table(name = "orders")
public class Order
        implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private LocalDateTime createdAt;

    private String customer;

    @OneToMany(fetch = FetchType.EAGER,
               cascade = CascadeType.ALL,
               mappedBy = "order",
               orphanRemoval = true)
    private Set<Item> items = new HashSet<>();

    protected Order() {

    }

    Order(User user, Map<Product, Long> productsWithQuantity) {
        this.createdAt = LocalDateTime.now();
        this.customer = user.getFirstname() + " " + user.getLastname();
        this.number = UUID.randomUUID().toString();
        this.items = productsWithQuantity.entrySet().stream()
                .map(entry -> new Item(this, entry.getKey(), entry.getValue()))
                .collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCustomer() {
        return customer;
    }

    public Set<Item> getItems() {
        return Collections.unmodifiableSet(items);
    }

}
