package com.egs.shoppingapplication;

import com.egs.shoppingapplication.model.Category;
import com.egs.shoppingapplication.model.Product;
import com.egs.shoppingapplication.model.RoleEnum;
import com.egs.shoppingapplication.model.User;
import com.egs.shoppingapplication.repository.CategoryRepository;
import com.egs.shoppingapplication.repository.ProductRepository;
import com.egs.shoppingapplication.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DatabaseRunner implements CommandLineRunner {

    private static final Logger logger =
            LoggerFactory.getLogger(DatabaseRunner.class);

    final Environment env;

    final UserRepository userRepository;
    final CategoryRepository categoryRepository;
    final ProductRepository productRepository;
    final PasswordEncoder passwordEncoder;

    public DatabaseRunner(UserRepository userRepository, PasswordEncoder passwordEncoder
            , CategoryRepository categoryRepository
            , ProductRepository productRepository, Environment env) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.env = env;
    }

    @Override
    public void run(String... args) {

        if (env.getActiveProfiles()[0].equals("dev")) {
            userRepository.save(User.builder()
                    .username("reza")
                    .password(passwordEncoder.encode("911411328mM@"))
                    .role(RoleEnum.ROLE_ADMIN)
                    .build());

            Category c1 = Category.builder()
                    .name("digital")
                    .build();
            Product p1 = Product.builder()
                    .name("mobilePhone")
                    .price(new BigDecimal(1000))
                    .build();
            c1.addProduct(p1);
            categoryRepository.save(c1);

            Category c2 = Category.builder()
                    .name("home")
                    .build();
            Product p2 = Product.builder()
                    .name("TV")
                    .price(new BigDecimal(3000))
                    .build();
            c2.addProduct(p2);
            categoryRepository.save(c2);

            logger.warn("add default user.");
        }
    }
}
