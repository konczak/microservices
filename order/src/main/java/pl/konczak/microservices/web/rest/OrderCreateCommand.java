package pl.konczak.microservices.web.rest;

import java.util.List;

public class OrderCreateCommand {

    private String customerId;

    private List<String> productIds;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }

}
