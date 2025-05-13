package com.example.api_stock.dto.sales;

import com.example.api_stock.domain.client.Client;
import com.example.api_stock.domain.desconto.Discont;
import com.example.api_stock.domain.user.User;
import com.example.api_stock.dto.discount.DiscountDTO;

import java.time.LocalDate;
import java.util.List;

public record SaleDTO (
        String id_client,
        LocalDate date_sale,
        List<ItenSaleDTO> items,
        String cpfClient,
        DiscountDTO discount
) { }
