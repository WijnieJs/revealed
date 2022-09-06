package com.example.two.controllers;


import com.example.two.dto.ProductDto;

import com.example.two.security.response.MessageResponse;
import com.example.two.services.ProductService;
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


    @PostMapping("/newproduct")
    public ResponseEntity<?> createNewProduct(@Valid  @RequestBody ProductDto productDto, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        ProductDto newProduct = productService.addNewProduct(productDto);

        return ResponseEntity.ok(new MessageResponse("Product created successfully!"));

    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> getAllProducts()  {
        List<ProductDto> body = productService.fetchAllProductsInShop();
        if (body.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
    }


    @GetMapping("/productById/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") long id)  {
        ProductDto product = productService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);

    }
    @PutMapping("/productEdit/{id}")
    public ResponseEntity<ProductDto> editProduct(@PathVariable("id") long id, @Valid @RequestBody ProductDto productDto) {
        ProductDto editProduct = productService.editProduct(id, productDto);

        return new ResponseEntity<>(editProduct, HttpStatus.OK);


    }




//    @GetMapping("/tags")
//    public ResponseEntity<List<Tag>> findTagsByPrdId()  {
//        List<Tag> tags = new ArrayList<>(productServiceImpl.getTagsByAll());
//
//        if (tags.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(tags, HttpStatus.OK);
//
//    }
}
