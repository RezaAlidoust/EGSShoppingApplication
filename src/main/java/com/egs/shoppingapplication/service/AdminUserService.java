package com.egs.shoppingapplication.service;

import com.egs.shoppingapplication.dto.request.AdminUserRequest;
import com.egs.shoppingapplication.dto.request.AdminUserUpdateRequest;
import com.egs.shoppingapplication.dto.response.AdminUserResponse;
import com.egs.shoppingapplication.exception.CustomException;
import com.egs.shoppingapplication.model.User;
import com.egs.shoppingapplication.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AdminUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    AdminUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AdminUserResponse create(AdminUserRequest request) {
        User user = userRepository.findUserByUsername(request.getUsername());
        if (user != null) {
            throw new CustomException("User existed", HttpStatus.UNPROCESSABLE_ENTITY, "");
        }
        user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .locked(request.isLocked())
                .build();
        return (new AdminUserResponse(userRepository.save(user)));
    }

    public AdminUserResponse update(String userId, AdminUserUpdateRequest request) {
        Optional<User> userOp = userRepository.findById(UUID.fromString(userId));
        if (userOp.isEmpty()) {
            throw new CustomException("User not found", HttpStatus.NOT_FOUND, "");
        }
        // Admin can not change username, password and etc. They can only block or unblock with block field.
        User user = userOp.get();
        user.setLocked(request.isLocked());
        return new AdminUserResponse(userRepository.save(user));
    }
}
