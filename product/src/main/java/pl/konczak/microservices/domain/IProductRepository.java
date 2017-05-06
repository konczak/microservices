package pl.konczak.microservices.domain;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IProductRepository
        extends ElasticsearchRepository<Product, String> {

    List<Product> findByNameLike(String name);

    List<Product> findBySeller(String seller);
}
