package com.example.two.services.serviceImplents;


import com.example.two.dto.ProductDto;

import com.example.two.exceptions.ApiRequestException;
import com.example.two.exceptions.ProductNotFoundException;
import com.example.two.models.Product;
import com.example.two.repository.ProductRepository;

import com.example.two.services.serviceInterfaces.ProductService;
import com.example.two.utils.Helper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {



    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

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
    public ProductDto findProductById(Integer id) {

            Product product =  productRepository.findById(id)
                    .orElseThrow(() -> new ApiRequestException("Could not find product with id " + id));

            return transferToDto(product);

    }

    @Override
    public ProductDto editProduct(int id, ProductDto dto) {

//        Product productInDb =  productRepository.findById(id);
////                .orElseThrow(() -> new ApiRequestException("Could not find product with id " + id));
               try {
//
//                productInDb.setTitle(dto.getTitle());
//                productInDb.setDescription(dto.getDescription());
//                productInDb.setPublished(dto.isPublished());
//                productInDb.setPrice(dto.getPrice());
////
////                productRepository.save(product);

                return dto;
//                return transferToDto(dto);
            } catch(Exception e) {
                throw new ProductNotFoundException("Something went wrong saving . ");
            }

    }


    @Override
    public ProductDto addNewProduct(ProductDto productDto) {
            try {

//                if(Helper.notNull(productRepository.getProductByTitle(productDto.getTitle()))) {
//                    throw new ProductNotFoundException("Product with this title already exists");
//                }

//                   Product product = productRepository.save(transferToProduct(productDto));

                    return productDto;
//                    return transferToDto(product);
            } catch(Exception e) {
                throw new ProductNotFoundException("Something is with this product ");

            }

    }

//    public Product transferToProduct(ProductDto dto) {
//            var newProduct = new Product();
//            newProduct.setTitle(dto.getTitle());
//            newProduct.setDescription(dto.getDescription());
//            newProduct.setPublished(dto.isPublished());
//            newProduct.setPrice(dto.getPrice());
//
//            return newProduct;
//    }

    private Product transferToProduct(ProductDto dto) {
        Product entity = modelMapper.map(dto, Product.class);

        return entity;
    }
private ProductDto transferToDto(Product product) {
        ProductDto dto = modelMapper.map(product, ProductDto.class);
        return dto;
}
//    public ProductDto transferToDto(Product product) {
//        ProductDto productDto = new ProductDto();
//        productDto.setId(product.getId());
//        productDto.setTitle(product.getTitle());
//        productDto.setPrice(product.getPrice());
//        productDto.setPublished(product.isPublished());
//        productDto.setDescription(product.getDescription());
//        return productDto;
//    }


}

