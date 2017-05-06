package pl.konczak.microservices.web.rest;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.konczak.microservices.domain.IOrderBO;
import pl.konczak.microservices.domain.IOrderRepository;
import pl.konczak.microservices.domain.Order;

@RestController
@RequestMapping("/order")
public class OrderApi {

    private final IOrderRepository orderRepository;

    private final IOrderBO orderBO;

    public OrderApi(IOrderRepository orderRepository, IOrderBO orderBO) {
        this.orderRepository = orderRepository;
        this.orderBO = orderBO;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Order> list() {
        return orderRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET,
                    value = "/{id}")
    public HttpEntity<Order> get(@PathVariable("id") Long id) {
        Order order = orderRepository.getOne(id);

        if (order == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(order);
    }

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<Order> order(@RequestBody OrderCreateCommand orderCreateCommand) {
        Order order = orderBO.create(orderCreateCommand.getCustomerId(), orderCreateCommand.getProductIds());
        return ResponseEntity.ok(order);
    }
}
