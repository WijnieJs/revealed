package com.example.two.services.serviceImplents;


import com.example.two.dto.ProductDto;

import com.example.two.dto.ResponseDto;
import com.example.two.dto.converters.DtoMapperService;
import com.example.two.dto.converters.TagToDto;
import com.example.two.exceptions.ApiRequestException;
import com.example.two.exceptions.ProductNotFoundException;
import com.example.two.exceptions.UserIdException;
import com.example.two.models.Product;
import com.example.two.repository.ProductRepository;
import com.example.two.services.serviceInterfaces.TagService;

import com.example.two.services.serviceInterfaces.ProductService;
import com.example.two.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {


    private final DtoMapperService dtoHandler;
    private final ProductRepository productRepository;
    private final TagService tagService;

    @Autowired
    public ProductServiceImpl(DtoMapperService dtoMapHandlerr, ProductRepository productRepository, TagService tagService) {
        this.dtoHandler = dtoMapHandlerr;
        this.productRepository = productRepository;
        this.tagService = tagService;
    }

    @Override
    public List<ProductDto> fetchAll() {
        try {

            List<Product> productInDb = productRepository.findAll();
            return getAllProductsToDto();

//            return dtoHandler.dtoConverter(products, ProductDto.class);
        } catch (Exception e) {
            throw new ProductNotFoundException("Something is wrong on the connection. ");
        }
    }

    public List<ProductDto> getAllProductsToDto() {
        try {
            List<Product> productInDb = productRepository.findAll();
            List<ProductDto> dtos = new ArrayList<>();

            for (Product product : productInDb) {
                ProductDto dtoObj = (ProductDto) dtoHandler.dtoClassConverter(product, ProductDto.class);
                dtoObj.setTags(addTagsToProduct(product));
                dtos.add(dtoObj);
            }
//            return ProductToDto.of(product);/

            return dtos;
        } catch (Exception e) {
            throw new ProductNotFoundException("Something is wrong on the connection. ");
        }
    }


    @Override
    public ProductDto findProductDtoById(Long   id) {

        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() ->  new ProductNotFoundException("Product with id " + id + " could not be found"));
            ProductDto dtoObj = (ProductDto) dtoHandler.dtoClassConverter(product, ProductDto.class);
            dtoObj.setTags(addTagsToProduct(product));

            return dtoObj;
        } catch (Exception e) {
            throw  new ApiRequestException("Something is wrong on the connection. ");
        }

    }

    @Override
    public Product findInDbProductById(Long productId) {
         try {
             return productRepository.findById(productId).get();
         } catch (Exception e) {
             throw new ProductNotFoundException("Product with id " + productId + " could not be found");
         }
    }

    public List<String> addTagsToProduct(Product product) {
        return product.getTags().stream().map(TagToDto::of).collect(Collectors.toList());

    }

    @Override
    public ProductDto addNewProduct(ProductDto productDto) {
        // also works, but for readability kept them seperated
//           productRepository.save((Product) dtoHandler.dtoClassConverter(productDto, Product.class));
        String errorMessage =  "Something went wrong saving the product";
        try {

            findExistingProductByTitle(productDto.getTitle());
            Product newProduct = (Product) dtoHandler.dtoClassConverter(productDto, Product.class);
            productRepository.save(newProduct);

            return (ProductDto) dtoHandler.dtoClassConverter(newProduct, ProductDto.class);
        } catch (ProductNotFoundException e) {
            throw new ProductNotFoundException(errorMessage);
        }
    }

    @Override
    public ResponseDto editProduct(ProductDto productDto) {
        long id = productDto.getId();
        try {
            Product productInDb = productRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));


            productRepository.save(editBuilderFactory(productInDb, productDto));

            return new ResponseDto("Successfully created", "think ");
        } catch (Exception e) {
            throw new ProductNotFoundException("Something went wrong saving . ");
        }
    }

    @Override
    public List<ProductDto> fetchProductsByTag(String tagName) {
        String errorMessage = "No Product with this tag name was found";

        try {
            List<ProductDto> productsFoundByTag = new ArrayList<>();

            for (ProductDto productsWithTag : getAllProductsToDto()) {
                for (String tagInProduct : productsWithTag.getTags()) {
                    if (productWithTagFound(tagName, tagInProduct)) {
                        productsFoundByTag.add(productsWithTag);

                    }
                }
            }
            return productsFoundByTag;
        } catch (ProductNotFoundException e) {
            throw new ProductNotFoundException(errorMessage);

        }

    }

    @Override
    public ProductDto fetchProductByTitle(String title) {
        String errorMessage = "Product not found";
        try {

            if (Helper.notNull(productRepository.getProductByTitleIgnoreCase(title))) {
                Product product =  productRepository.getProductByTitleIgnoreCase(title);
                ProductDto dtoObj = (ProductDto) dtoHandler.dtoClassConverter(product, ProductDto.class);
                dtoObj.setTags(addTagsToProduct(product));

                return dtoObj;
            } else  {
                throw new ProductNotFoundException(errorMessage);
            }



        } catch (ProductNotFoundException e) {
            throw new ProductNotFoundException(errorMessage);
        }

    }

        public void findExistingProductByTitle(String title) {
        String errorMessage = "Product with title: " + title + "exists already";

            if (Helper.notNull(productRepository.getProductByTitleIgnoreCase(title))) {
                throw new UserIdException(errorMessage);
            }

        }

//    public void productNotFound(String title) {
//        String errorMessage = "Product " + title + "exists already";
//        try {
//            Product prodInDb = productRepository.getProductByTitleIgnoreCase(title);
//        } catch (Exception e) {
//            throw new ProductNotFoundException(errorMessage);
//        }





    public boolean productWithTagFound(String role, String productTag) {
        return productTag.toUpperCase().equals(role.toUpperCase());
    }


    private Product editBuilderFactory(Product productInDb, ProductDto productDto) {
        if (Helper.notNull(productDto.getTitle())) {
            productInDb.setTitle(productDto.getTitle());
        }
        if (Helper.notNull(productDto.getDescription())) {
            productInDb.setDescription(productDto.getDescription());
        }
        if (Helper.notNull(productDto.getImageURL())) {
            productInDb.setImageURL(productDto.getImageURL());
        }
        if (!productDto.isPublished()) {
            productInDb.setPublished(productInDb.isPublished());
        }


        return productInDb;

    }
}


// Leaving these here as reference to previous implementation of mapper
//    private Product transferToProduct(ProductDto dto) {
//        Product entity = modelMapper.map(dto, Product.class);
//
//        return entity;
//    }
//private ProductDto transferToDto(Product product) {
//        ProductDto dto = modelMapper.map(product, ProductDto.class);
//        return dto;
//}


/// Leaving these here as reference since this was the style of the homework assignments
//    public ProductDto transferToDto(Product product) {
//        ProductDto productDto = new ProductDto();
//        productDto.setId(product.getId());
//        productDto.setTitle(product.getTitle());
//        productDto.setPrice(product.getPrice());
//        productDto.setPublished(product.isPublished());
//        productDto.setDescription(product.getDescription());
//        return productDto;
//    }

//    public Product transferToProduct(ProductDto dto) {
//            var newProduct = new Product();
//            newProduct.setTitle(dto.getTitle());
//            newProduct.setDescription(dto.getDescription());
//            newProduct.setPublished(dto.isPublished());
//            newProduct.setPrice(dto.getPrice());
//
//            return newProduct;
//    }

