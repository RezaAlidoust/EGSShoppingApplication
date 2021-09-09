package com.egs.shoppingapplication.dto.response;

import com.egs.shoppingapplication.model.Category;
import com.egs.shoppingapplication.model.Product;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AdminCategoryWithProductsResponse {
    private String id;
    private String name;
    private long count;
    private List<AdminProductResponse> products = null;

    public AdminCategoryWithProductsResponse(Page<Product> products, long count, Category category) {
        if (products != null)
            this.products = products.getContent().stream().map(AdminProductResponse::new).collect(Collectors.toList());
        this.count = count;
        this.id = category.getId().toString();
        this.name = category.getName();
    }
}
