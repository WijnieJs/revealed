package com.example.two.controllers;


import com.example.two.dto.ProductDto;

import com.example.two.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@RequestMapping("/api/shop")

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/test")
    public String allAccess() {
        return
                "accesing in visitor mode";
    }




    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> getAllProducts()  {
        List<ProductDto> body = productService.fetchAllUser();
        if (body.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
    }




//    @GetMapping("/productById/{id}")
//    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") long id)  {
//        ProductDto product = productService.findProductById(id);
//        return new ResponseEntity<>(product, HttpStatus.OK);
//
//    }

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
