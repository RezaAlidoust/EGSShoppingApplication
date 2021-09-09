package com.egs.shoppingapplication;

import com.egs.shoppingapplication.model.RoleEnum;
import com.egs.shoppingapplication.model.User;
import com.egs.shoppingapplication.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseRunner implements CommandLineRunner {

    private static final Logger logger =
            LoggerFactory.getLogger(DatabaseRunner.class);

    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    public DatabaseRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
//        userRepository.save(User.builder()
//                .username("reza")
//                .password(passwordEncoder.encode("911411328mM@"))
//                .role(RoleEnum.ROLE_ADMIN)
//                .build());
        logger.warn("add default user.");
    }
}
