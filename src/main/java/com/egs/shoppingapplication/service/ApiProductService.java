package com.egs.shoppingapplication.service;

import com.egs.shoppingapplication.dto.request.ApiCommentRequest;
import com.egs.shoppingapplication.dto.request.ApiProductSearchRequest;
import com.egs.shoppingapplication.dto.request.ApiRateRequest;
import com.egs.shoppingapplication.dto.response.ApiProductListResponse;
import com.egs.shoppingapplication.exception.CustomException;
import com.egs.shoppingapplication.model.Product;
import com.egs.shoppingapplication.model.ProductComment;
import com.egs.shoppingapplication.model.ProductRating;
import com.egs.shoppingapplication.model.User;
import com.egs.shoppingapplication.model.dao.ProductSpecification;
import com.egs.shoppingapplication.model.dao.ProductSpecificationsBuilder;
import com.egs.shoppingapplication.repository.ProductRepository;
import com.egs.shoppingapplication.repository.UserRepository;
import com.egs.shoppingapplication.util.SearchOperation;
import com.egs.shoppingapplication.util.SpecSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ApiProductService {
    final ProductRepository productRepository;
    final UserRepository userRepository;

    public ApiProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }


    public ApiProductListResponse search(ApiProductSearchRequest request, int page, int size, String sort, String direction) {
        Pageable paging;
        if ("DESC".equals(direction)) {
            paging = PageRequest.of(page, size, Sort.by(sort).descending());
        } else {
            paging = PageRequest.of(page, size, Sort.by(sort).ascending());
        }


        ProductSpecificationsBuilder specification = new ProductSpecificationsBuilder();
        if (request.getMinPrice() != null) {
            ProductSpecification minPrice =
                    new ProductSpecification(
                            new SpecSearchCriteria("price", SearchOperation.GREATER_THAN, request.getMinPrice()));
            specification.with(minPrice);
        }
        if (request.getMaxPrice() != null) {
            ProductSpecification maxPrice =
                    new ProductSpecification(
                            new SpecSearchCriteria("price", SearchOperation.LESS_THAN, request.getMaxPrice()));
            specification.with(maxPrice);
        }

        if (request.getName() != null && !request.getName().isEmpty()) {
            ProductSpecification name =
                    new ProductSpecification(
                            new SpecSearchCriteria("name", SearchOperation.CONTAINS, request.getName()));
            specification.with(name);
        }


        Page<Product> products = productRepository.findAll(specification.build(), paging);
        long count = productRepository.count(specification.build());

        return new ApiProductListResponse(products, count);
    }

    public void createComment(String productId, ApiCommentRequest request, String username) {
        User user = userRepository.findUserByUsername(username);
        Optional<Product> productOptional = productRepository.findById(UUID.fromString(productId));
        if (productOptional.isEmpty()) {
            throw new CustomException("product not found!", HttpStatus.NOT_FOUND, "");
        }
        ProductComment productComment = ProductComment
                .builder()
                .message(request.getMessage())
                .build();
        productComment.setProduct(productOptional.get());
        productComment.setUser(user);
    }

    public void createRate(String productId, ApiRateRequest request, String username) {
        User user = userRepository.findUserByUsername(username);
        Optional<Product> productOptional = productRepository.findById(UUID.fromString(productId));
        if (productOptional.isEmpty()) {
            throw new CustomException("product not found!", HttpStatus.NOT_FOUND, "");
        }
        ProductRating rating = ProductRating
                .builder()
                .rating(request.getRate())
                .build();
        rating.setProduct(productOptional.get());
        rating.setUser(user);
    }
}
