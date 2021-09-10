package com.egs.shoppingapplication.controller.admin;

import com.egs.shoppingapplication.dto.request.AdminProductRequest;
import com.egs.shoppingapplication.dto.request.SortEnumRequest;
import com.egs.shoppingapplication.dto.response.AdminCategoryWithProductsResponse;
import com.egs.shoppingapplication.dto.response.AdminProductResponse;
import com.egs.shoppingapplication.service.AdminProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController(value = "AdminProductController")
@RequestMapping(path = {"/admin/categories/{categoryId}/products"}, produces = APPLICATION_JSON_VALUE)
public class AdminProductController {
    final AdminProductService productService;

    public AdminProductController(AdminProductService productService) {
        this.productService = productService;
    }


    @Operation(summary = "Returns a list of products and sorted/filtered based on the query parameters")
    @ApiResponse(responseCode = "200", description = "list of products", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AdminCategoryWithProductsResponse.class))})
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden!")
    @GetMapping
    public ResponseEntity<AdminCategoryWithProductsResponse> all(@PathVariable String categoryId,
                                                                 @RequestParam(required = false, name = "page",
                                                                         defaultValue = "0") int page,
                                                                 @RequestParam(required = false, name = "size",
                                                                         defaultValue = "20") int size,
                                                                 @RequestParam(required = false, name = "sort",
                                                                         defaultValue = "createdAt") SortEnumRequest sort,
                                                                 @RequestParam(required = false, name = "direction",
                                                                         defaultValue = "desc") String direction
    ) {
        final AdminCategoryWithProductsResponse list =
                productService.all(categoryId, page, size, sort.name(), direction);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "get product")
    @ApiResponse(responseCode = "200", description = "product is created", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AdminProductResponse.class))})
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden!")
    @ApiResponse(responseCode = "422", description = "Some inputs have errors!")
    @GetMapping(value = {"/{productId}"})
    public ResponseEntity<AdminProductResponse> get(@PathVariable String categoryId, @PathVariable String productId) {
        final AdminProductResponse product = productService.get(categoryId, productId);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @Operation(summary = "Add product to current category")
    @ApiResponse(responseCode = "201", description = "product is created", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AdminProductResponse.class))})
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden!")
    @ApiResponse(responseCode = "422", description = "Some inputs have errors!")
    @PostMapping
    public ResponseEntity<AdminProductResponse> create(@PathVariable String categoryId
            , @Valid @RequestBody AdminProductRequest request) {
        final AdminProductResponse product = productService.create(categoryId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @Operation(summary = "Update product")
    @ApiResponse(responseCode = "200", description = "product is created", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AdminProductResponse.class))})
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden!")
    @ApiResponse(responseCode = "422", description = "Some inputs have errors!")
    @PutMapping(value = {"/{productId}"})
    public ResponseEntity<AdminProductResponse> create(@PathVariable String categoryId, @PathVariable String productId, @Valid @RequestBody AdminProductRequest request) {
        final AdminProductResponse product = productService.update(categoryId, productId, request);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @Operation(summary = "Update product")
    @ApiResponse(responseCode = "200", description = "product is created", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AdminProductResponse.class))})
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden!")
    @ApiResponse(responseCode = "422", description = "Some inputs have errors!")
    @DeleteMapping(value = {"/{productId}"})
    public ResponseEntity<Void> delete(@PathVariable String categoryId, @PathVariable String productId) {
        productService.delete(categoryId, productId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
