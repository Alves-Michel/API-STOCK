package com.example.api_stock.service;

import com.example.api_stock.domain.client.Client;
import com.example.api_stock.domain.desconto.Discont;
import com.example.api_stock.domain.product.Product;
import com.example.api_stock.domain.sale.ItemSale;
import com.example.api_stock.domain.sale.Sale;
import com.example.api_stock.error.BusinessException;
import com.example.api_stock.repositories.ClientRepository;
import com.example.api_stock.repositories.ItemSaleRepository;
import com.example.api_stock.repositories.ProductRepository;
import com.example.api_stock.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;


@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ItemSaleRepository itemSaleRepository;

    @Transactional
    public Sale createSaleWithItems(Sale sale, List<ItemSale> itemSales, String cpfClient, Discont discont) {

        if (sale.getClient() == null || !clientRepository.existsById(sale.getClient().getId_client())) {
            throw new IllegalArgumentException("Client not found");
        }

        Client client;
        if (sale.getClient() == null) {
            // Cliente não informado => usar PadrãoPDV
            client = clientRepository.findById("0ed8ef03-29f9-11f0-8e0c-fc34977b5155").orElseThrow(() -> new IllegalArgumentException("Client not found"));
        } else {
            // Cliente informado => usar o enviado
            client = clientRepository.findById(sale.getClient().getId_client())
                    .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        }



        // Se o cliente for o padrão, o CPF ainda precisa ser atualizado
        if (client.getId_client().equals("0ed8ef03-29f9-11f0-8e0c-fc34977b5155") && cpfClient != null && !cpfClient.isEmpty()) {
            sale.setCpfClient(cpfClient); // Atualiza o CPF na venda
        } else if (cpfClient != null && !cpfClient.isEmpty()) {
            sale.setCpfClient(cpfClient); // Caso o cliente não seja padrão, também atualiza o CPF
        }
        sale.setClient(client);
        // Primeiro salva a venda sem os itens para gerar o ID
        sale.setTotal_sale(0.0);
        Sale savedSale = saleRepository.save(sale);

        // Agora processa os itens com a venda já persistida
        for (ItemSale item : itemSales) {
            Product product = productRepository.findById(item.getProduct().getId_product())
                    .orElseThrow(() -> new BusinessException("Product not found"));

            item.setProduct(product);
            item.setSale(savedSale);
            BigDecimal priceUnit = product.getPrice_product();
            item.setPriceUnityItem(priceUnit);

            item.setSubTotalItem(calculateSubTotal(item));

            itemSaleRepository.save(item);
        }

        // todos os subtotais, calcula e atualiza o total
        Double totalSale = calculateTotalVenda(itemSales, discont);
        savedSale.setTotal_sale(totalSale);

        // faz o update do total
        return saleRepository.save(savedSale);
    }


    private Double calculateTotalVenda(List<ItemSale> itemSales, Discont discont) {
        double totalVenda = 0.0;
        for (ItemSale item : itemSales) {
            totalVenda += item.getSubTotalItem().doubleValue();

            Product product = item.getProduct();
            int currentStock = product.getAmount_product();

            if(currentStock < item.getAmount_item()){
                throw new BusinessException("Insufficient stock: " + product.getNameproduct());
            }
            product.setAmount_product(currentStock - item.getAmount_item());
            productRepository.save(product);

        }
        System.out.println("Total antes do desconto: " + totalVenda);
        double valorFinal = discont != null ? discont.applyDiscount(totalVenda) : totalVenda;
        System.out.println("Total após desconto: " + valorFinal);
        return valorFinal;
    }

    private BigDecimal calculateSubTotal(ItemSale item) {
        BigDecimal priceUnit = item.getProduct().getPrice_product();

        return priceUnit.multiply(BigDecimal.valueOf(item.getAmount_item()));

    }

}
