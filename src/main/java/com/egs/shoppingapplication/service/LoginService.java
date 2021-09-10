package com.egs.shoppingapplication.service;

import com.egs.shoppingapplication.dto.request.LoginRequest;
import com.egs.shoppingapplication.dto.response.ApiUserResponse;
import com.egs.shoppingapplication.dto.response.JwtResponse;
import com.egs.shoppingapplication.exception.CustomException;
import com.egs.shoppingapplication.model.RoleEnum;
import com.egs.shoppingapplication.model.User;
import com.egs.shoppingapplication.repository.UserRepository;
import com.egs.shoppingapplication.security.jwt.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    final UserRepository userRepository;
    final JwtTokenProvider jwtTokenProvider;
    final AuthenticationManagerBuilder authenticationManagerBuilder;
    final PasswordEncoder passwordEncoder;

    public LoginService(UserRepository userRepository,
                        JwtTokenProvider jwtTokenProvider,
                        AuthenticationManagerBuilder authenticationManagerBuilder,
                        PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.passwordEncoder = passwordEncoder;
    }

    public JwtResponse loginWithPassword(LoginRequest authenticationRequest) {
        String jwt = setContextAuthentication(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        return (new JwtResponse(jwt));
    }

    public ApiUserResponse signUp(LoginRequest authenticationRequest) {
        User user = userRepository.save(User.builder()
                .username(authenticationRequest.getUsername())
                .password(passwordEncoder.encode(authenticationRequest.getPassword()))
                .locked(false)
                .role(RoleEnum.ROLE_USER).build());

        return (new ApiUserResponse(user));
    }

    private String setContextAuthentication(String username, String password) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password);

            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return jwtTokenProvider.createToken(authentication);
        } catch (Exception e) {
            throw new CustomException("UNAUTHORIZED", HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
