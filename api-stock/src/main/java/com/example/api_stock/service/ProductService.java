
package com.example.api_stock.service;

import com.example.api_stock.domain.category.Category;
import com.example.api_stock.domain.category.SubCategory;
import com.example.api_stock.domain.product.Product;
import com.example.api_stock.dto.product.ProductDTO;
import com.example.api_stock.repositories.ProductRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;



@Service
@AllArgsConstructor

public class ProductService {

    @Autowired
    private final ProductRepository productRepository;


    public List<ProductDTO> listProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ProductDTO(
                        product.getCodigo_de_barras(),
                        product.getId_product(),
                        product.getNameproduct(),
                        product.getDescribe_product(),
                        product.getPrice_product(),
                        product.getAmount_product(),
                        product.getData_validade(),
                        product.getSubcategory() != null ? product.getSubcategory().getName() : "Sem subcategoria" // Verificação
                ))
                .collect(Collectors.toList());
    }


    public void updateProductById(String productId,
                                  ProductDTO productDTO) {

        var productEntity = productRepository.findById(productId);

        if (productEntity.isPresent()) {
            var product = productEntity.get();
            Optional.ofNullable(productDTO.codigo_de_barras()).ifPresent(product::setCodigo_de_barras);
            Optional.ofNullable(productDTO.name()).ifPresent(product::setNameproduct);
            Optional.ofNullable(productDTO.describe()).ifPresent(product::setDescribe_product);
            Optional.ofNullable(productDTO.price()).ifPresent(product::setPrice_product);
            Optional.ofNullable(productDTO.amount()).ifPresent(product::setAmount_product);
            Optional.ofNullable(productDTO.datavalidade()).ifPresent(product::setData_validade);
            Optional.ofNullable(productDTO.subcategoryId()).ifPresent(id -> product.setSubcategory(new SubCategory()));


            productRepository.save(product);
        }

    }

    public void deleteById(String productId) {
        try{
            var id_product = productId;
            var productExists = productRepository.existsById(id_product);

            if (productExists) {
                productRepository.deleteById(id_product);

            }else{
                throw new RuntimeException("Product not found");
            }
        }catch (NumberFormatException e){
            throw new RuntimeException("Invalid product id");
        }

    }

    public List<ProductDTO> buscarProduto(String name) {
        List<Product> products= productRepository.findByNameproductContainingIgnoreCase(name);
        if (products.isEmpty()) {
            throw new RuntimeException("Product not found" + name);
        }

        return products.stream()
                .map(product -> new ProductDTO(
                        product.getCodigo_de_barras(),
                        product.getId_product(),
                        product.getNameproduct(),
                        product.getDescribe_product(),
                        product.getPrice_product(),
                        product.getAmount_product(),
                        product.getData_validade(),
                        product.getSubcategory() != null ? product.getSubcategory().getName() : "Sem subcategoria"

        ))
        .collect(Collectors.toList());
    }
}
