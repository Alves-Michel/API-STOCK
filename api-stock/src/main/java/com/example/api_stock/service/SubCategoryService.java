package com.example.api_stock.service;
import com.example.api_stock.dto.category.SubCategoryDTO;
import com.example.api_stock.repositories.SubCategoryRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubCategoryService {

    @Autowired
    private final SubCategoryRepository subCategoryRepository;

    public List<SubCategoryDTO> listSubCategories() {

        return subCategoryRepository.findAll().stream().map(
                subCategory -> new SubCategoryDTO(
                        subCategory.getSubcategory(),
                        subCategory.getName(),
                        subCategory.getCategory().getName()
                ))
                .collect(Collectors.toList());

    }

    public void deleteById(String id_subCategory) {

        try {
            var subCategoryExists = subCategoryRepository.existsById(id_subCategory);
            if (subCategoryExists) {
                subCategoryRepository.deleteById(id_subCategory);

            }else {
                throw new RuntimeException("Subcategory does not exist");
            }
        }catch (NumberFormatException e){
            throw new RuntimeException("Subcategory ID is not valid");
        }



    }

}
