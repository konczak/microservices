package pl.konczak.microservices.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.konczak.microservices.domain.IProductBO;
import pl.konczak.microservices.domain.IProductRepository;
import pl.konczak.microservices.domain.Product;

@RestController
@RequestMapping("/product")
public class ProductApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductApi.class);

    private final IProductBO productBO;

    private final IProductRepository productRepository;

    public ProductApi(IProductBO productBO, IProductRepository productRepository) {
        this.productBO = productBO;
        this.productRepository = productRepository;
    }

    private List<Product> convert(Iterable<Product> iterable) {
        List<Product> products = new ArrayList<>();

        iterable.iterator()
                .forEachRemaining(products::add);

        return products;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> list() {
        LOGGER.info("GET /product");
        return convert(productRepository.findAll());
    }

    @RequestMapping(method = RequestMethod.GET,
                    value = "/{id}")
    public HttpEntity<Product> get(@PathVariable("id") String id) {
        LOGGER.info("GET /product/{}", id);
        Product product = productRepository.findOne(id);

        if (product == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(product);
    }

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<Product> add(@RequestBody ProductAddCommand productAddCommand) {
        LOGGER.info("POST /product");
        Product product = productBO.add(productAddCommand.getName(), productAddCommand.getSeller(), productAddCommand.getPrice());

        return ResponseEntity.ok(product);
    }

    @RequestMapping(method = RequestMethod.GET,
                    value = "/search",
                    params = {"name"})
    public List<Product> searchByName(@RequestParam("name") String name) {
        LOGGER.info("GET /product/search?name={}", name);
        return productRepository.findByNameLike(name);
    }

    @RequestMapping(method = RequestMethod.GET,
                    value = "/seller")
    public List<Product> searchBySeller(@RequestParam("seller") String seller) {
        LOGGER.info("GET /product/search?seller={}", seller);
        return productRepository.findBySeller(seller);
    }
}
