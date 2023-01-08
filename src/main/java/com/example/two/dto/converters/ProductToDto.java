package com.example.two.dto.converters;

import com.example.two.dto.ProductDto;
import com.example.two.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


// Not used anymore left it here for reference for teacher to see my prior methods of handling the mapper.\
// See ProductServiceImpl class at line 183 and below for the legacy implementation

public class ProductToDto {

    private static DtoMapperService dtoHandler;

    public static List<ProductDto> of(List<Product> products) {

        List<ProductDto> dtos = new ArrayList<>();
        for(Product productys: products) {
            ProductDto proDto = new ProductDto();
            proDto.setId(productys.getId());
            proDto.setPrice(productys.getPrice());
            proDto.setTitle(productys.getTitle());

            proDto.setDescription(productys.getDescription());
            if (!productys.isPublished()) {
                proDto.setPublished(proDto.isPublished());
            }

            proDto.setTags(productys.getTags().stream().map(TagToDto::of).collect(Collectors.toList()));
            dtos.add(proDto);
        }
        return   dtos;
    }


}
