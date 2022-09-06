package com.example.two.services;

import com.example.two.dto.ProductDto;


import java.util.List;

public interface ProductService {


    ProductDto addNewProduct(ProductDto productDto);

    List<ProductDto> fetchAllProductsInShop();

    ProductDto findProductById(Long id);

    ProductDto editProduct(Long id, ProductDto productDto);
}
