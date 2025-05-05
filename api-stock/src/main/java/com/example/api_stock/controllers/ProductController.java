package com.example.api_stock.controllers;

import com.example.api_stock.domain.category.SubCategory;
import com.example.api_stock.domain.product.Product;
import com.example.api_stock.dto.product.ProductDTO;
import com.example.api_stock.repositories.ProductRepository;
import com.example.api_stock.repositories.SubCategoryRepository;
import com.example.api_stock.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final  ProductRepository productRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private ProductService productService;

    @PostMapping("/register")
    public ResponseEntity registerProduct(@RequestBody ProductDTO body) {
        try {
            Optional<SubCategory> subCategory = subCategoryRepository.findById(body.subcategoryId());
            if (!subCategory.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sub-Categoria não encontrada");
            }
            System.out.println("Subcategoria encontrada: " + subCategory.get().getName());

            // Criar o novo produto
            Product newProduct = new Product();
            newProduct.setCodigo_de_barras(body.codigo_de_barras());
            newProduct.setNameproduct(body.name());
            newProduct.setAmount_product(body.amount());
            newProduct.setPrice_product(body.price());
            newProduct.setDescribe_product(body.describe());
            newProduct.setData_validade(body.datavalidade());
            newProduct.setSubcategory(subCategory.get());

            // Salvar o produto no banco de dados
            productRepository.save(newProduct);

            // Retornar a resposta de sucesso
            return ResponseEntity.status(HttpStatus.CREATED).body("Produto criado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar produto: " + e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductDTO>> listProducts() {
        var products = productService.listProducts();
        return ResponseEntity.ok().body(products);

    }

    @PutMapping("/update/{id_product}")
    public ResponseEntity<Void> updateProduct( @PathVariable("id_product") String product_id,
                                               @RequestBody ProductDTO body) {
        productService.updateProductById(product_id, body);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/{id_product}")
    public ResponseEntity deleteProductById(@PathVariable("id_product") String productId) {
        productService.deleteById(productId);
        return ResponseEntity.ok().body("Produto deletado com sucesso");

    }



    @GetMapping("/busca")
    public ResponseEntity<List<ProductDTO>> buscarProdutos(@RequestParam(required = false) String name) {
        System.out.println("Buscando produtos com o nome: " + name); // Log para depuração

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name parameter is required");
        }

        List<ProductDTO> products = productService.buscarProduto(name);
        System.out.println("Produtos encontrados: " + products); // Log para depuração

        return ResponseEntity.ok(products);
    }

}
