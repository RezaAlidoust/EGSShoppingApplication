package com.egs.shoppingapplication.service;

import com.egs.shoppingapplication.dto.request.AdminCategoryRequest;
import com.egs.shoppingapplication.dto.request.SortEnumRequest;
import com.egs.shoppingapplication.dto.response.AdminCategoryListResponse;
import com.egs.shoppingapplication.dto.response.AdminCategoryResponse;
import com.egs.shoppingapplication.model.Category;
import com.egs.shoppingapplication.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AdminCategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    AdminCategoryService adminCategoryService;

    @Test
    void all() {
        given(categoryRepository.findAll(any(Pageable.class))).willReturn(null);
        long count = 0;
        given(categoryRepository.count()).willReturn(count);

        AdminCategoryListResponse adminCategoryResponse = adminCategoryService.all(0, 10, SortEnumRequest.createdAt.name(), "desc");

        verify(categoryRepository).findAll(any(Pageable.class));
        verify(categoryRepository).count();

        assertThat(adminCategoryResponse).isNotNull();
    }

    @Test
    void create() {
        UUID uuid = UUID.randomUUID();
        Category category = Category.builder()
                .id(uuid)
                .name("cars")
                .build();
        given(categoryRepository.save(any(Category.class))).willReturn(category);

        AdminCategoryResponse adminCategoryResponse = adminCategoryService.create(new AdminCategoryRequest());

        verify(categoryRepository).save(any(Category.class));

        assertThat(adminCategoryResponse).isNotNull();
    }
}