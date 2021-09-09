package com.egs.shoppingapplication.dto.response;


import com.egs.shoppingapplication.model.User;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class AdminUserResponse {

    private String id;
    private String username;
    private String role;
    private boolean locked;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public AdminUserResponse(User user) {
        if (user != null) {
            this.id = user.getId().toString();
            this.username = user.getUsername();
            this.role = user.getRole().name();
            this.locked = user.isLocked();
            this.createdAt = user.getCreatedAt();
            this.updatedAt = user.getUpdatedAt();
        }
    }
}
