package com.example.api_stock.controllers;

import com.example.api_stock.domain.category.Category;
import com.example.api_stock.domain.category.SubCategory;
import com.example.api_stock.dto.category.SubCategoryDTO;
import com.example.api_stock.repositories.CategoryRepository;
import com.example.api_stock.repositories.SubCategoryRepository;
import com.example.api_stock.service.SubCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/subCategory")
public class SubCategoryController {
    private final SubCategoryService subCategoryService;
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/list")
    public ResponseEntity<List<SubCategoryDTO>> listSubCategories()  {
        var subCategory = subCategoryService.listSubCategories();
        return ResponseEntity.ok().body(subCategory);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerSubCategory(@Valid @RequestBody SubCategoryDTO body) {
        try {
            Category category = categoryRepository.findById(body.categoryId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

            // Cria a subcategoria
            SubCategory subCategory = new SubCategory();
            subCategory.setName(body.name());
            subCategory.setCategory(category); // Associa à categoria

            // Salva a subcategoria
            subCategoryRepository.save(subCategory);

            return ResponseEntity.status(HttpStatus.CREATED).body("SubCategoria criada com sucesso");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar subCategoria: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id_subCategory}")
    public ResponseEntity deleteSubCategory(@PathVariable ("id_subCategory") String id_subCategory) {
        subCategoryService.deleteById(id_subCategory);
        return ResponseEntity.ok().body("SubCategoria deletada com sucesso");
    }
}
