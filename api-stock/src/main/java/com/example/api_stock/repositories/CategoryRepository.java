package com.example.api_stock.repositories;

import com.example.api_stock.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByName(String name);

}
