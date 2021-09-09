package com.egs.shoppingapplication.repository;

import com.egs.shoppingapplication.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
