package com.egs.shoppingapplication.dto.response;

import com.egs.shoppingapplication.model.Product;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ApiProductListResponse {
    private List<ApiProductResponse> products = null;
    private long count;

    public ApiProductListResponse(Page<Product> products, long count) {
        if (products != null)
            this.products = products.getContent().stream().map(ApiProductResponse::new).collect(Collectors.toList());
        this.count = count;
    }
}
