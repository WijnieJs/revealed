package com.example.two.services;


import com.example.two.dto.ProductDto;

import com.example.two.exceptions.ApiRequestException;
import com.example.two.exceptions.ProductNotFoundException;
import com.example.two.exceptions.UserIdException;
import com.example.two.models.Product;
import com.example.two.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<ProductDto> fetchAllProductsInShop() {
        try {
            List<Product> products = productRepository.findAll();
            List<ProductDto> dtos = new ArrayList<>();
            for (Product product : products) {
                ProductDto productDto = transferToDto(product);
                dtos.add(productDto);
            }
            return dtos;
        } catch(Exception e) {
            throw new ProductNotFoundException("Something is wrong on the connection. ");
        }
    }

    @Override
    public ProductDto findProductById(Long id) {

            Product product =  productRepository.findById(id)
                    .orElseThrow(() -> new ApiRequestException("Could not find product with id " + id));

            return transferToDto(product);

    }

    @Override
    public ProductDto editProduct(Long id, ProductDto productDto) {

        Product product =  productRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Could not find product with id " + id));
            try {

                product.setTitle(productDto.getTitle());
//                product.setDescription(productDto.getDescription());
//                product.setPrice(productDto.getPrice());
//                product.setPublished(productDto.isPublished());
//                product.setImageURL(productDto.getImageURL());
//
//                productRepository.save(product);
            System.out.println(productDto.getTitle() + " IN   hereree" );

                return transferToDto(product);
            } catch(Exception e) {
                throw new ProductNotFoundException("Something went wrong saving . ");
            }

    }


    @Override
    public ProductDto addNewProduct(ProductDto productDto) {
            try {
        Product product = transferToProduct(productDto);


            productRepository.save(product);
            return transferToDto(product);

            } catch(Exception e) {
                throw new ProductNotFoundException("Something is with this product ");

            }

    }

    public Product transferToProduct(ProductDto dto) {
            var newProduct = new Product();
            newProduct.setTitle(dto.getTitle());
            newProduct.setDescription(dto.getDescription());
            newProduct.setPublished(dto.isPublished());
            newProduct.setPrice(dto.getPrice());

            return newProduct;
    }

    public ProductDto transferToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setPublished(product.isPublished());
        productDto.setDescription(product.getDescription());
        return productDto;
    }


}

