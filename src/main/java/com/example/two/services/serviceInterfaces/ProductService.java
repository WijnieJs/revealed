package com.example.two.services.serviceInterfaces;

import com.example.two.dto.ProductDto;


import java.util.List;

public interface ProductService {


    ProductDto addNewProduct(ProductDto productDto);

    List<ProductDto> fetchAllProductsInShop();

    ProductDto findProductById(Integer id);

    ProductDto editProduct(int id, ProductDto productDto);
}
