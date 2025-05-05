package com.example.api_stock.domain.product;


import com.example.api_stock.domain.category.Category;
import com.example.api_stock.domain.category.SubCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name ="product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(columnDefinition = "CHAR(36)", updatable = false, nullable = false)
    private String id_product;

    @ManyToOne
    @JoinColumn(name = "id_subcategory", referencedColumnName = "id_subcategory")
    private SubCategory subcategory;

    @Column(name = "name_product")
    private String nameproduct;

    private String describe_product;

    private BigDecimal price_product;

    private int amount_product;

    private String codigo_de_barras;

    private LocalDate data_validade;



}
