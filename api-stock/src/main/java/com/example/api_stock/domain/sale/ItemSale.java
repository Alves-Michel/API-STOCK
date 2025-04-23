package com.example.api_stock.domain.sale;

import com.example.api_stock.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name ="item_sale")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemSale {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(columnDefinition = "CHAR(36)", updatable = false, nullable = false)
    private String id_sale;

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id_product")
    private Product idproduct;

    @ManyToOne
    @JoinColumn(name = "id_sale", referencedColumnName = "id_sale")
    private Sale idsale;

    private int amount_item;

    @Column(name = "sub_total_item", precision = 10, scale = 2)
    private BigDecimal subTotalItem;

    @Column(name = "price_unity_item", precision = 10, scale = 2)
    private BigDecimal priceUnityItem;


}
