package com.example.api_stock.service;

import com.example.api_stock.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public void deleteById(String id_category) {
        var categoryExists = categoryRepository.existsById(id_category);
        if (categoryExists) {
            categoryRepository.deleteById(id_category);
        } else {
            throw new RuntimeException("Category with id " + id_category + " does not exist");
        }
    }

}
