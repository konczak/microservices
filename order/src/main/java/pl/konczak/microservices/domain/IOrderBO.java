package pl.konczak.microservices.domain;

import java.util.List;

public interface IOrderBO {

    Order create(String customerId, List<String> productIds);

}
