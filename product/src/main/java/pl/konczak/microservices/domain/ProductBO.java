package pl.konczak.microservices.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductBO
        implements IProductBO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductBO.class);

    private final IProductRepository productRepository;

    public ProductBO(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product add(String name, String seller, int price) {
        Product product = new Product(name, seller, price);

        product = productRepository.save(product);

        LOGGER.info("Add Product <{}> <{}> <{}>", product.getId(), product.getName(), product.getSeller());

        return product;
    }

}
