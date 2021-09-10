package com.egs.shoppingapplication.dto.response;

import com.egs.shoppingapplication.model.Category;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AdminCategoryListResponse {
    private List<AdminCategoryResponse> categories = null;
    private long count;

    public AdminCategoryListResponse(Page<Category> categories, long count) {
        if (categories != null)
            this.categories = categories.getContent().stream().map(AdminCategoryResponse::new).collect(Collectors.toList());
        this.count = count;
    }
}
