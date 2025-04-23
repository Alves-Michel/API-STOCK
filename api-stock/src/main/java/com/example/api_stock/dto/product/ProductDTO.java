package com.example.api_stock.dto.product;
import java.time.LocalDate;

public record ProductDTO (
        String codigo_de_barras,
        String idProduct,
        String name,
        String describe,
        double price,
        int amount,
        LocalDate datavalidade,
        String subcategoryId
) {}



