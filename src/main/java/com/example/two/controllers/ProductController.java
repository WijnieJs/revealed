package com.example.two.controllers;


import com.example.two.dto.ProductDto;

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
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;
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
//    System.out.println(result + "IN THE  FFF RESULT");

        ProductDto newProduct = productService.addNewProduct(productDto);

          return ResponseEntity.ok(new MessageResponse("Product " + newProduct.getTitle() + "created successfully!"));

    }
    @PutMapping("/editProduct")
    public ResponseEntity<ProductDto> editProduct(@Valid @RequestBody ProductDto productDto) {

        ProductDto editProduct = productService.editProduct( productDto);

        return new ResponseEntity<>(editProduct, HttpStatus.OK);


    }

    @GetMapping("/allProductsInShop")
    public ResponseEntity<List<ProductDto>> getAllProducts()  {


        List<ProductDto> body = productService.fetchAllProductsInShop();
        if (body.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
    }


    @GetMapping("/productById/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") int id)  {
        ProductDto product = productService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);

    }


    @GetMapping("/getProductsByTitle")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(value = "title", required = false) Optional<String> title)  {


        List<Product> body = productService.getAllProductsByTitle(title.get());
//        if (body.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
        return new ResponseEntity<List<Product>>(body, HttpStatus.OK);
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
