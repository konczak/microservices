package pl.konczak.microservices.web.rest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Order {

    private final Long id;

    private final String number;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    private final String customer;

    private final List<Item> items;

    public Order(pl.konczak.microservices.domain.Order order) {
        this.id = order.getId();
        this.number = order.getNumber();
        this.createdAt = order.getCreatedAt();
        this.customer = order.getCustomer();
        this.items = order.getItems()
                .stream()
                .map(Item::new)
                .collect(Collectors.toList());
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

    public List<Item> getItems() {
        return items;
    }

}
