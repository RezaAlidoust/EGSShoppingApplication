package com.egs.shoppingapplication.service;

import com.egs.shoppingapplication.dto.request.AdminProductRequest;
import com.egs.shoppingapplication.dto.response.AdminCategoryWithProductsResponse;
import com.egs.shoppingapplication.dto.response.AdminProductResponse;
import com.egs.shoppingapplication.exception.CustomException;
import com.egs.shoppingapplication.model.Category;
import com.egs.shoppingapplication.model.Product;
import com.egs.shoppingapplication.repository.CategoryRepository;
import com.egs.shoppingapplication.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    final ProductRepository productRepository;

    final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public AdminCategoryWithProductsResponse all(String categoryId, int page, int size, String sort, String direction) {
        Category category = getCategory(categoryId);

        Pageable paging;
        if ("DESC".equals(direction)) {
            paging = PageRequest.of(page, size, Sort.by(sort).descending());
        } else {
            paging = PageRequest.of(page, size, Sort.by(sort).ascending());
        }

        Page<Product> products = productRepository.findAllByCategory(category, paging);
        long count = productRepository.countAllByCategory(category);

        return new AdminCategoryWithProductsResponse(products, count, category);
    }

    public AdminProductResponse get(String categoryId, String productId) {
        return new AdminProductResponse(getProduct(productId, categoryId));
    }

    public AdminProductResponse create(String categoryId, AdminProductRequest request) {
        Category category = getCategory(categoryId);
        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();
        category.addProduct(product);
        categoryRepository.save(category);

        return new AdminProductResponse(product);
    }

    public AdminProductResponse update(String categoryId, String productId, AdminProductRequest request) {
        Product product = getProduct(productId, categoryId);
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        productRepository.save(product);

        return new AdminProductResponse(productRepository.save(product));
    }

    public void delete(String categoryId, String productId) {
        Category category = getCategory(categoryId);
        Product product = getProduct(productId, categoryId);

        category.removeProduct(product);
        categoryRepository.save(category);
    }

    private Category getCategory(String categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(categoryId));
        if (categoryOptional.isEmpty()) {
            throw new CustomException("Category not found!", HttpStatus.NOT_FOUND, "");
        }
        return categoryOptional.get();
    }

    private Product getProduct(String productId, String categoryId) {
        Product product = productRepository.findProductByIdAndCategory_Id(UUID.fromString(productId), UUID.fromString(categoryId));
        if (product == null) {
            throw new CustomException("product not found!", HttpStatus.NOT_FOUND, "");
        }
        return product;
    }
}
