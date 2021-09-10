package com.egs.shoppingapplication.dto.response;

import com.egs.shoppingapplication.model.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApiProductResponse {
    private String id;
    private String name;
    private BigDecimal price;

    public ApiProductResponse(Product product) {
        if (product != null) {
            this.id = product.getId().toString();
            this.name = product.getName();
            this.price = product.getPrice();
        }
    }
}
