package com.example.api_stock.repositories;

import com.example.api_stock.domain.sale.ItemSale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemSaleRepository extends JpaRepository<ItemSale, String> {
}
