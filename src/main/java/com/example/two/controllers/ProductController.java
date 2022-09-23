package com.example.two.controllers;


import com.example.two.dto.ProductDto;

import com.example.two.dto.ResponseDto;
import com.example.two.exceptions.ApiException;
import com.example.two.exceptions.ApiRequestException;
import com.example.two.exceptions.ProductNotFoundException;
import com.example.two.models.Product;
import com.example.two.security.response.MessageResponse;
import com.example.two.services.serviceInterfaces.ProductService;
import com.example.two.utils.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
//@RequestMapping("/api/shop")

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public")
public class ProductController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String allAccess() {
        return
                "accesing in visitor mode";
    }
/// set

    @PostMapping("/newproduct")
    public ResponseEntity<?> createNewProduct(@Valid  @RequestBody ProductDto productDto, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;



        try {
                ProductDto newProduct = productService.addNewProduct(productDto);

                return ResponseEntity.ok(new MessageResponse("Product " + newProduct.getTitle() + "created successfully!"));

            } catch(Exception e) {
                  throw new ProductNotFoundException(e.getMessage());
            }


    }
    @PatchMapping("/editProduct")
    public ResponseEntity<?> editProduct(@Valid @RequestBody ProductDto productDto) {

        ResponseDto editProduct = productService.editProduct( productDto);

        return ResponseEntity.ok(new MessageResponse("Product created successfully!"));


    }

    @GetMapping("/allProductsInShop")
    public ResponseEntity<List<ProductDto>> getAllProducts()  {


        List<ProductDto> body = productService.fetchAll();
        if (body.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
    }


    @GetMapping("/productById/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") long id)  {
        ProductDto product = productService.findProductDtoById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);

    }



    @GetMapping("/getByTag/{tagName}")
    public ResponseEntity<List<ProductDto>> getProductByTag(@PathVariable("tagName") String tagName)  {

        List<ProductDto> product = productService.fetchProductsByTag(tagName);
        return ResponseEntity.ok().body(product);

    }

    @GetMapping("/getByTitle/{title}")
    public ResponseEntity<ProductDto> getProductByTitle(@PathVariable("title") String title)  {


         ProductDto product = productService.fetchProductByTitle(title);


        return ResponseEntity.ok().body(product);
    }




}