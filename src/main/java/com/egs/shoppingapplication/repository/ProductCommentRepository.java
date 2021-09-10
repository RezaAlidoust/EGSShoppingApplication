package com.egs.shoppingapplication.repository;

import com.egs.shoppingapplication.model.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductCommentRepository extends JpaRepository<ProductComment, UUID> {
}
