package com.example.api_stock.dto.sales;

import com.example.api_stock.domain.product.Product;
import com.example.api_stock.domain.sale.Sale;

import java.math.BigDecimal;

public record ItenSaleDTO (
        String id_product,
        int amount_item,
        BigDecimal subtotal


){
}
