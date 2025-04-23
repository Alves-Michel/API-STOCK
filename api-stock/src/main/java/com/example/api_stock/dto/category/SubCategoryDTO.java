package com.example.api_stock.dto.category;

import jakarta.validation.constraints.NotBlank;

public record SubCategoryDTO(
        String subCategoryId,
        @NotBlank(message = "Nome da Sub Categoria é obrigatorio")
        String name,
        String categoryId
)
{ }
