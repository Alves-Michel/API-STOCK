package com.example.api_stock.dto.category;


import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(
        @NotBlank(message = "O nome da categoria é obrigatório")
        String name
) {}
