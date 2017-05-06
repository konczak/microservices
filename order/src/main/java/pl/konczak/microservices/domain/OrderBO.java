package pl.konczak.microservices.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.microservices.client.product.IProductClient;
import pl.konczak.microservices.client.product.Product;
import pl.konczak.microservices.client.user.IUserClient;
import pl.konczak.microservices.client.user.User;

@Transactional
@Service
public class OrderBO
        implements IOrderBO {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderBO.class);

    private final IProductClient productClient;

    private final IUserClient userClient;

    private final IOrderRepository orderRepository;

    public OrderBO(IProductClient productClient, IUserClient userClient, IOrderRepository orderRepository) {
        this.productClient = productClient;
        this.userClient = userClient;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order create(String customerId, List<String> productIds) {
        User user = userClient.getUser(customerId);
        Map<String, Long> productIdsWithQuantity = productIds.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        Map<Product, Long> productsWithQuantity = productIdsWithQuantity.entrySet().stream()
                .collect(Collectors.toMap(entry -> productClient.getProduct(entry.getKey()), Map.Entry::getValue));

        Order order = new Order(user, productsWithQuantity);

        order = orderRepository.save(order);

        LOGGER.info("Add Order <{}> <{}>", order.getId(), order.getCustomer());

        return order;
    }
}
