package pl.konczak.microservices.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "products",
          type = "product")
public class Product {

    @Id
    private String id;

    private String name;

    private String seller;

    private int price;

    public Product() {
    }

    public Product(String name, String seller, int price) {
        this.name = name;
        this.seller = seller;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSeller() {
        return seller;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", seller=" + seller + ", price=" + price + '}';
    }

}
