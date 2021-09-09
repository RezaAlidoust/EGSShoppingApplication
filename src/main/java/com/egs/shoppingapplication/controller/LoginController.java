package com.egs.shoppingapplication.controller;

import com.egs.shoppingapplication.dto.request.LoginRequest;
import com.egs.shoppingapplication.dto.response.JwtResponse;
import com.egs.shoppingapplication.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
public class LoginController {

    final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @Operation(summary = "Authenticate")
    @ApiResponse(responseCode = "200", description = "Authentication is done", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = JwtResponse.class))})
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = JwtResponse.class))})
    @ApiResponse(responseCode = "422", description = "Invalid username/password supplied", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = JwtResponse.class))})
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<JwtResponse> loginWithPassword(@Valid @RequestBody LoginRequest authenticationRequest) {
        JwtResponse loginWithPassword = loginService.loginWithPassword(authenticationRequest);
        return ResponseEntity.status(HttpStatus.OK).body(loginWithPassword);
    }
}
