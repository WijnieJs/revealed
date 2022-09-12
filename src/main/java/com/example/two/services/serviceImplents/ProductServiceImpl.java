package com.example.two.services.serviceImplents;


import com.example.two.dto.ProductDto;

import com.example.two.dto.ResponseDto;
import com.example.two.dto.converters.DtoMapperService;
import com.example.two.dto.converters.ProductToDto;
import com.example.two.dto.converters.TagToDto;
import com.example.two.exceptions.ApiRequestException;
import com.example.two.exceptions.ProductNotFoundException;
import com.example.two.models.Product;
import com.example.two.repository.ProductRepository;
import com.sun.xml.bind.v2.schemagen.episode.SchemaBindings;
import org.modelmapper.ModelMapper;

import com.example.two.services.serviceInterfaces.ProductService;
import com.example.two.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {


    private DtoMapperService dtoHandler;
    private final ProductRepository productRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(DtoMapperService dtoMapHandlerr, ProductRepository productRepository) {
        this.dtoHandler = dtoMapHandlerr;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> fetchAll() {
        try {
            List<Product> product = productRepository.findAll();
            return ProductToDto.of(product);

//            return dtoHandler.dtoConverter(products, ProductDto.class);
        } catch (Exception e) {
            throw new ProductNotFoundException("Something is wrong on the connection. ");
        }
    }


    @Override
    public ProductDto findProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Could not find product with id " + id));
//extract into method for better resusability
        ProductDto dtoObj = (ProductDto) dtoHandler.dtoClassConverter(product, ProductDto.class);
        dtoObj.setTags(product.getTags().stream().map(TagToDto::of).collect(Collectors.toList()));

        return dtoObj;
    }

    @Override
    public ProductDto addNewProduct(ProductDto productDto) {
        // also works, but for readability kept them seperated
//           productRepository.save((Product) dtoHandler.dtoClassConverter(productDto, Product.class));
        try {
            Product newProduct = (Product) dtoHandler.dtoClassConverter(productDto, Product.class);

            productRepository.save(newProduct);

            return (ProductDto) dtoHandler.dtoClassConverter(newProduct, ProductDto.class);
        } catch (ProductNotFoundException e) {
            throw new ProductNotFoundException("Something went wrong on the server ");
        }
    }

    @Override
    public ResponseDto editProduct(ProductDto productDto) {
        int id = productDto.getId();
        try {
            Product productInDb = productRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));


            productRepository.save(editBuilderFactory(productInDb, productDto));

            return new ResponseDto("Successfully created", "think ");
        } catch (Exception e) {
            throw new ProductNotFoundException("Something went wrong saving . ");
        }
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


    @Override
    public List<Product> getAllProductsByTitle(String title) {

        try {
            List<Product> products = productRepository.findAllProductsByTitle(title);
            return products;
        } catch (Exception e) {
            throw new ProductNotFoundException("No product with this name was found ");
        }

    }

}
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

