package com.egs.shoppingapplication.service;

import com.egs.shoppingapplication.dto.request.AdminCategoryRequest;
import com.egs.shoppingapplication.dto.response.AdminCategoryListResponse;
import com.egs.shoppingapplication.dto.response.AdminCategoryResponse;
import com.egs.shoppingapplication.model.Category;
import com.egs.shoppingapplication.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public AdminCategoryListResponse all(int page, int size, String sort, String direction) {
        Pageable paging;
        if ("DESC".equals(direction)) {
            paging = PageRequest.of(page, size, Sort.by(sort).descending());
        } else {
            paging = PageRequest.of(page, size, Sort.by(sort).ascending());
        }

        Page<Category> categories = categoryRepository.findAll(paging);
        long count = categoryRepository.count();

        return new AdminCategoryListResponse(categories, count);
    }

    public AdminCategoryResponse create(AdminCategoryRequest request) {
        Category category = Category.builder()
                .name(request.getName())
                .build();

        return new AdminCategoryResponse(categoryRepository.save(category));
    }
}
