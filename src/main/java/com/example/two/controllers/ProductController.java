package com.example.two.controllers;


import com.example.two.dto.ProductDto;

import com.example.two.dto.ResponseDto;
import com.example.two.exceptions.ProductNotFoundException;
import com.example.two.models.FileUploadResponse;
import com.example.two.models.Product;
import com.example.two.security.response.MessageResponse;
import com.example.two.services.serviceInterfaces.ProductService;
import com.example.two.services.serviceInterfaces.UserService;
import com.example.two.utils.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
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

    @Autowired
    UserService userService;

    @Autowired
    PhotoController controller;


    @GetMapping("/")
    public String allAccess() {
        return
                "accesing in visitor mode";
    }



    @PostMapping("/newproduct")
    public ResponseEntity<?> createNewProduct(@Valid  @RequestBody ProductDto productDto, BindingResult result) {
//        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
//        if (errorMap != null) return errorMap;

        try {
            System.out.println(productDto.getTitle());
                ProductDto newProduct = productService.addNewProduct(productDto);
                return ResponseEntity.ok(new MessageResponse("Product " + newProduct.getTitle() + "created successfully!"));
            } catch(Exception e) {
                  throw new ProductNotFoundException(e.getMessage());
            }
    }
    @PostMapping("/editProduct")
    public ResponseEntity<?> editProduct(@Valid @RequestBody ProductDto productDto) {
        ResponseDto editProduct = productService.editProduct( productDto);
        return ResponseEntity.ok(new MessageResponse("Product created successfully!"));
    }
    @PostMapping("/{id}/photo")
    public void assignPhotoToProduct(@PathVariable("id") Long studentNumber,
                                     @RequestBody MultipartFile file) {

        FileUploadResponse photo = controller.singleFileUpload(file);

        productService.assignPhotoToStudent(photo.getFileName(), studentNumber);

    }

    @GetMapping("/newAll")
    @Transactional
    public List<Product> getProds() {
        List<Product> students;
        students = productService.getProductsAll();

        return students;
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
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id)  {
        Product product = productService.findInDbProductById(id);

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