package com.example.api_stock.repositories;

import com.example.api_stock.domain.product.Product;
import com.example.api_stock.dto.product.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByNameproductContainingIgnoreCase(String nameProduct);

}
