package com.example.two.controllers;

import com.example.two.dto.ProductDto;
import com.example.two.dto.ResponseDto;
import com.example.two.dto.inputs.CartItemInputDto;
import com.example.two.models.Cart;
import com.example.two.models.User;
import com.example.two.security.response.MessageResponse;
import com.example.two.services.serviceInterfaces.CartService;
import com.example.two.services.serviceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public")
public class CartController {

    @Autowired
    UserService userService;
//
    @Autowired
    CartService cartService;

//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/byusername/{username}")
    public ResponseEntity<?> getUserByToken(@PathVariable String username) {
        User user = userService.findByUsername(username);

        System.out.println(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }



    @PostMapping("/addToCart")
    public ResponseEntity<ResponseDto> addTocart(@RequestBody CartItemInputDto addTooAdd) {

        ResponseDto response =  cartService.addTocart(addTooAdd);

        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/removeFromCart")
    public ResponseEntity<ResponseDto> removeTocart(@RequestBody CartItemInputDto addTooAdd){

        ResponseDto response =   cartService.removeFromCart(addTooAdd);

        return ResponseEntity.ok(response);

}
}