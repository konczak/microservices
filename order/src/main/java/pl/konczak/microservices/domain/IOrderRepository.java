package pl.konczak.microservices.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository
        extends JpaRepository<Order, Long> {

}
