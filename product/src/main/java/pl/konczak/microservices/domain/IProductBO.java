package pl.konczak.microservices.domain;

public interface IProductBO {

    Product add(String name, String seller, int price);

}
