package pl.konczak.microservices.client.product;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${client.product.name}",
             decode404 = true)
public interface IProductClient {

    @RequestMapping(method = RequestMethod.GET,
                    value = "/product/{id}")
    Product getProduct(@PathVariable("id") String id);
}
