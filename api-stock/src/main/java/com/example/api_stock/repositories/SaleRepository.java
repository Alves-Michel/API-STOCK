package com.example.api_stock.repositories;

import com.example.api_stock.domain.sale.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, String> {
}
