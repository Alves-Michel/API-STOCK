package com.example.api_stock.controllers;

import com.example.api_stock.domain.category.Category;
import com.example.api_stock.dto.category.CategoryDTO;
import com.example.api_stock.repositories.CategoryRepository;
import com.example.api_stock.repositories.SubCategoryRepository;
import com.example.api_stock.service.CategoryService;
import com.example.api_stock.service.SubCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final SubCategoryRepository subCategoryRepository;


    @GetMapping("/list")
    public ResponseEntity getAllCategories() {

        return ResponseEntity.ok(categoryRepository.findAll());
    }



    @PostMapping("/register")
    public ResponseEntity<String> registerCategory(@Valid @RequestBody CategoryDTO body) {
        Optional<Category> categoryRepositoryOptional = categoryRepository.findByName(body.name());
        if (categoryRepositoryOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Category already exists");

        }else {

            try {
                Category newCategory = new Category();
                newCategory.setName(body.name());
                categoryRepository.save(newCategory);
                return ResponseEntity.status(HttpStatus.CREATED).body("Categoria criada com sucesso");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar categoria: " + e.getMessage());
            }
        }

}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") String id_Category) {
        categoryService.deleteById(id_Category);
        return ResponseEntity.ok().body("Categoria deletada com sucesso");
    }
}









