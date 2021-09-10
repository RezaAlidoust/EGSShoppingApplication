package com.egs.shoppingapplication.controller.api;


import com.egs.shoppingapplication.dto.request.ApiCommentRequest;
import com.egs.shoppingapplication.dto.request.ApiProductSearchRequest;
import com.egs.shoppingapplication.dto.request.ApiRateRequest;
import com.egs.shoppingapplication.dto.request.SortEnumRequest;
import com.egs.shoppingapplication.dto.response.AdminUserResponse;
import com.egs.shoppingapplication.dto.response.ApiProductListResponse;
import com.egs.shoppingapplication.service.ApiProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController(value = "ApiProductController")
@RequestMapping(path = {"/api/products"}, produces = APPLICATION_JSON_VALUE)
public class ApiProductController {
    final ApiProductService apiProductService;

    public ApiProductController(ApiProductService apiProductService) {
        this.apiProductService = apiProductService;
    }

    @Operation(summary = "Search for products")
    @ApiResponse(responseCode = "200", description = "search is done!", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AdminUserResponse.class))})
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden!")
    @ApiResponse(responseCode = "422", description = "Some inputs have errors!")
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiProductListResponse> search(@RequestParam(required = false, name = "page",
            defaultValue = "0") int page,
                                                         @RequestParam(required = false, name = "size",
                                                                 defaultValue = "20") int size,
                                                         @RequestParam(required = false, name = "sort",
                                                                 defaultValue = "createdAt") SortEnumRequest sort,
                                                         @RequestParam(required = false, name = "direction",
                                                                 defaultValue = "desc") String direction,
                                                         @Valid @RequestBody ApiProductSearchRequest request) {
        final ApiProductListResponse user = apiProductService.search(request, page, size, sort.name(), direction);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Operation(summary = "Comment")
    @ApiResponse(responseCode = "201", description = "Comment is created!")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden!")
    @ApiResponse(responseCode = "422", description = "Some inputs have errors!")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/{productId}/comment")
    public ResponseEntity<Void> createComment(@PathVariable String productId,
                                              @Valid @RequestBody ApiCommentRequest request,
                                              Authentication authentication) {
        apiProductService.createComment(productId, request, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Rate")
    @ApiResponse(responseCode = "201", description = "Rate is created!")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden!")
    @ApiResponse(responseCode = "422", description = "Some inputs have errors!")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/{productId}/rate")
    public ResponseEntity<Void> createRate(@PathVariable String productId,
                                           @Valid @RequestBody ApiRateRequest request,
                                           Authentication authentication) {
        apiProductService.createRate(productId, request, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
