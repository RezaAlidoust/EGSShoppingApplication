package com.egs.shoppingapplication.dto.request;

import com.egs.shoppingapplication.model.RoleEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AdminUserRequest {

    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    @Length(min = 3, message = "Minimum username length: 3 characters")
    private String username;

    @Length(min = 8, message = "Minimum password length: 8 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "password is not secure")
    private String password;

    @NotNull
    private RoleEnum role;

    @NotNull
    private boolean locked = false;
}
