package com.example.two.services;


import com.example.two.dto.ProductDto;
import com.example.two.exceptions.ProjectNotFoundException;
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


    public List<ProductDto> findAllProducts() {
            try {
                List<Product> products = productRepository.findAll();
                List<ProductDto> dtos = new ArrayList<>();
                 for (Product product : products) {
                    ProductDto productDto = transferToDto(product);
                    dtos.add(productDto);
                }
                return dtos;
            } catch(Exception e) {
            throw new ProjectNotFoundException("Something is wrong ");
            }

    }

    @Override
    public ProductDto findProductById(long id) {
        try {
            Product product = productRepository.findById(id).get();
            return transferToDto(product);
        } catch (Exception e ) {
            throw new ProjectNotFoundException("Product not in this shop ");
        }

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

//    @Override
//    public ProductDto findProductById(Long productId) {
//
//        try {
////
//           return getDtoFromProduct(productRepository.findById(productId).get()) ;
//
//        } catch(Exception e) {
//            throw new ProjectNotFoundException("No product found with this id ");
//
//        }
//
//    }
//
//
//
//    public static ProductDto getDtoFromProduct(Product product) {
//        ProductDto productDto = new ProductDto(product);
//
//        return productDto;
//    }




//        public List<Product> getProducts() {
//            return productRepository.findProductsByTagsId(2L);
//        }


//    @Override
//    public List<Product> findProductsByTagsId(Long tagId) {
//
//        return  productService.getAll(2L);
//    }



}

