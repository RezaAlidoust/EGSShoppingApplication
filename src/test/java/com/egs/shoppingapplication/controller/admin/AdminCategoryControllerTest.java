package com.egs.shoppingapplication.controller.admin;

import com.egs.shoppingapplication.dto.request.AdminCategoryRequest;
import com.egs.shoppingapplication.dto.response.AdminCategoryListResponse;
import com.egs.shoppingapplication.dto.response.AdminCategoryResponse;
import com.egs.shoppingapplication.model.Category;
import com.egs.shoppingapplication.service.AdminCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AdminCategoryControllerTest {

    @Mock
    AdminCategoryService adminCategoryService;

    @InjectMocks
    AdminCategoryController adminCategoryController;

    MockMvc mockMvc;

    Category category;

    @BeforeEach
    void setUp() {
        category = Category.builder()
                .id(UUID.randomUUID())
                .name("cars")
                .build();

        mockMvc = MockMvcBuilders.standaloneSetup(adminCategoryController).build();
    }

    @Test
    void all() throws Exception {
        AdminCategoryListResponse response = new AdminCategoryListResponse(null, 0);
        given(adminCategoryService.all(any(Integer.class), any(Integer.class),
                any(String.class), any(String.class))).willReturn(response);

        mockMvc.perform(get("/admin/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void create() throws Exception {
        AdminCategoryResponse response = new AdminCategoryResponse(category);
        given(adminCategoryService.create(any(AdminCategoryRequest.class))).willReturn(response);

        mockMvc.perform(post("/admin/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"name\": \"cars\"" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}