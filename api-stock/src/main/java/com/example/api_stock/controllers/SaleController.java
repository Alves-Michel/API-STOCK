package com.example.api_stock.controllers;

import com.example.api_stock.domain.sale.Sale;
import com.example.api_stock.dto.DtoConverter;
import com.example.api_stock.dto.sales.SaleDTO;
import com.example.api_stock.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    public Sale createSale(@RequestBody SaleDTO saleDTO){
        Sale sale = DtoConverter.fromDTO(saleDTO);
        var itemSales = DtoConverter.fromItemDTOList(saleDTO.items());

        return saleService.createSaleWithItems(sale, itemSales, sale.getCpfClient());


    }








}
