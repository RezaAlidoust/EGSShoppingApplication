package com.egs.shoppingapplication.dto.response;

import com.egs.shoppingapplication.model.User;
import lombok.Data;

@Data
public class ApiUserResponse {

    private String username;
    private String role;
    private boolean locked;

    public ApiUserResponse(User user) {
        if (user != null) {
            this.username = user.getUsername();
            this.role = user.getRole().name();
            this.locked = user.isLocked();
        }
    }
}
