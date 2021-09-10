package com.egs.shoppingapplication.controller.admin;

import com.egs.shoppingapplication.dto.request.AdminCategoryRequest;
import com.egs.shoppingapplication.dto.request.SortEnumRequest;
import com.egs.shoppingapplication.dto.response.AdminCategoryListResponse;
import com.egs.shoppingapplication.dto.response.AdminCategoryResponse;
import com.egs.shoppingapplication.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController(value = "AdminCategoryController")
@RequestMapping(path = {"/admin/categories"}, produces = APPLICATION_JSON_VALUE)
public class AdminCategoryController {

    final CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Returns a list of categories and sorted/filtered based on the query parameters")
    @ApiResponse(responseCode = "200", description = "list of categories"
            , content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AdminCategoryListResponse.class))})
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden!")
    @GetMapping
    public ResponseEntity<AdminCategoryListResponse> all(@RequestParam(required = false, name = "page", defaultValue = "0") int page,
                                                         @RequestParam(required = false, name = "size",
                                                                 defaultValue = "20") int size,
                                                         @RequestParam(required = false, name = "sort",
                                                                 defaultValue = "createdAt") SortEnumRequest sort,
                                                         @RequestParam(required = false, name = "direction",
                                                                 defaultValue = "desc") String direction
    ) {
        final AdminCategoryListResponse list = categoryService.all(page, size, sort.name(), direction);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Add category")
    @ApiResponse(responseCode = "201", description = "category is created"
            , content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AdminCategoryResponse.class))})
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden!")
    @ApiResponse(responseCode = "422", description = "Some inputs have errors!")
    @PostMapping
    public ResponseEntity<AdminCategoryResponse> create(@Valid @RequestBody AdminCategoryRequest request) {
        final AdminCategoryResponse category = categoryService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
}
