package com.example.api_stock.dto;

import com.example.api_stock.domain.client.Client;
import com.example.api_stock.domain.product.Product;
import com.example.api_stock.domain.sale.ItemSale;
import com.example.api_stock.domain.sale.Sale;
import com.example.api_stock.dto.sales.ItenSaleDTO;
import com.example.api_stock.dto.sales.SaleDTO;

import java.util.List;
import java.util.stream.Collectors;

public class DtoConverter {

    public static Sale fromDTO(SaleDTO dto){
        Sale sale = new Sale();
        sale.setDate_sale(dto.date_sale());

        Client client = new Client();
        client.setId_client(dto.id_client());
        sale.setCpfClient(dto.cpfClient());
        sale.setClient(client);
        return sale;
    }


    public static List<ItemSale> fromItemDTOList(List<ItenSaleDTO> dtos){
        return dtos.stream().map(dto ->{
            ItemSale item = new ItemSale();
            Product product = new Product();
            product.setId_product(dto.id_product());
            item.setProduct(product);
            item.setAmount_item(dto.amount_item());
            return item;
        }).collect(Collectors.toUnmodifiableList());

    }
}
