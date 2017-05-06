package pl.konczak.microservices.web.rest;

public class Item {

    private final Long id;

    private final String name;

    private final long quantity;

    private final int unitPrice;

    public Item(pl.konczak.microservices.domain.Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.quantity = item.getQuantity();
        this.unitPrice = item.getUnitPrice();
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
