package com.example.api_stock.dto.discount;

import java.math.BigDecimal;

public record DiscountDTO (
    Double value,
    String type
){}

