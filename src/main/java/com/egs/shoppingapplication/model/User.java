package com.egs.shoppingapplication.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User extends BaseEntity {

    @Length(min = 8, message = "Minimum password length: 8 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "password is not secure")
    private String password;

    @Length(min = 3, message = "Minimum username length: 3 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private RoleEnum role;

    @Column(nullable = false)
    private boolean locked = false;

    @Column(nullable = false)
    private boolean enabled = false;

    @Builder
    public User(UUID id, Timestamp createdAt, Timestamp updatedAt, String username,
                String password, RoleEnum role, boolean locked, boolean enabled) {
        super(id, createdAt, updatedAt);
        this.username = username;
        this.password = password;
        this.role = role;
        this.locked = locked;
        this.enabled = enabled;
    }
}
