package com.example.api_stock.repositories;

import com.example.api_stock.domain.category.Category;
import com.example.api_stock.domain.category.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubCategoryRepository extends JpaRepository<SubCategory, String> {
    Object findByCategory(Category category);
}
