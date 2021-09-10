package com.egs.shoppingapplication.repository;

import com.egs.shoppingapplication.model.ProductRating;
import com.egs.shoppingapplication.model.ProductRatingKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRatingRepository extends JpaRepository<ProductRating, ProductRatingKey> {
}
