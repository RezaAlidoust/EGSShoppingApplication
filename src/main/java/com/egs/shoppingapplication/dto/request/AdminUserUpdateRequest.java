package com.egs.shoppingapplication.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AdminUserUpdateRequest {
    @NotNull
    private boolean locked = false;
}
