package com.egs.shoppingapplication.repository;

import com.egs.shoppingapplication.model.Category;
import com.egs.shoppingapplication.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Page<Product> findAllByCategory(Category category, Pageable pageable);

    long countAllByCategory(Category category);

    Product findProductByIdAndCategory_Id(UUID productId, UUID categoryId);
}
