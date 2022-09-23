package com.example.two.services.serviceInterfaces;

import com.example.two.dto.ProductDto;
import com.example.two.dto.ResponseDto;
import com.example.two.models.Product;


import java.util.List;

public interface ProductService {

    ProductDto addNewProduct(ProductDto productDto);
    List<ProductDto> fetchAll();
    ProductDto findProductDtoById(Long  productId);
    Product findInDbProductById(Long  productId);
    ResponseDto editProduct(ProductDto productDto);
    List<ProductDto> fetchProductsByTag(String roleName);


    ProductDto fetchProductByTitle(String title);

}
