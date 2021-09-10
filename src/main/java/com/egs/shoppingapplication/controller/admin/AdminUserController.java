package com.egs.shoppingapplication.controller.admin;

import com.egs.shoppingapplication.dto.request.AdminUserRequest;
import com.egs.shoppingapplication.dto.request.AdminUserUpdateRequest;
import com.egs.shoppingapplication.dto.response.AdminUserResponse;
import com.egs.shoppingapplication.service.AdminUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController(value = "AdminUserController")
@RequestMapping(path = {"/admin/users"}, produces = APPLICATION_JSON_VALUE)
public class AdminUserController {
    final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }


    @Operation(summary = "Create the user")
    @ApiResponse(responseCode = "201", description = "User is created", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AdminUserResponse.class))})
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AdminUserResponse.class))})
    @ApiResponse(responseCode = "403", description = "Forbidden!", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AdminUserResponse.class))})
    @ApiResponse(responseCode = "422", description = "Some inputs have errors!", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AdminUserResponse.class))})
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminUserResponse> create(@Valid @RequestBody AdminUserRequest request) {
        final AdminUserResponse user = adminUserService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Operation(summary = "Update user details, Can be used for block and unblock a user")
    @ApiResponse(responseCode = "201", description = "User is created", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AdminUserResponse.class))})
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AdminUserResponse.class))})
    @ApiResponse(responseCode = "403", description = "Forbidden!", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AdminUserResponse.class))})
    @ApiResponse(responseCode = "422", description = "Some inputs have errors!", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AdminUserResponse.class))})
    @PutMapping(value = "/{userId}")
    public ResponseEntity<AdminUserResponse> update(@PathVariable String userId, @Valid @RequestBody AdminUserUpdateRequest request) {
        final AdminUserResponse user = adminUserService.update(userId, request);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
