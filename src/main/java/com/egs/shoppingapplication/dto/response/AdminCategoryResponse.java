package com.egs.shoppingapplication.dto.response;

import com.egs.shoppingapplication.model.Category;
import lombok.Data;

@Data
public class AdminCategoryResponse {
    private String id;
    private String name;

    public AdminCategoryResponse(Category category) {
        this.id = category.getId().toString();
        this.name = category.getName();
    }
}
