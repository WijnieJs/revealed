package com.example.two.controllers;

import com.example.two.dto.inputs.CartItemInputDto;
import com.example.two.models.Cart;
import com.example.two.models.Product;
import com.example.two.models.User;
import com.example.two.security.response.MessageResponse;
import com.example.two.services.serviceInterfaces.ShopService;
import com.example.two.services.serviceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.stream.IntStream;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public")
public class ShopController {

    @Autowired
    UserService userService;
//
    @Autowired
    ShopService shopService;

//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/byusername/{username}")
    public ResponseEntity<?> getUserByToken(@PathVariable String username) {
        User user = userService.findByUsername(username);

        System.out.println(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


//    @PostMapping("/addToCart")
//    public ResponseEntity<Cart> addTocart(@RequestBody ModifyCartRequest request) {
//        User user = userService.findByUsername(request.getUsername());
//        if(user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        Optional<Product> product = userService.findById(request.getItemId());
//        if(!item.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        Cart cart = user.getCart();
//        IntStream.range(0, request.getQuantity())
//                .forEach(i -> cart.addItem(item.get()));
//        cartRepository.save(cart);
//
//        return ResponseEntity.ok(cart);
//    }



//    @GetMapping("/mod")
//    @PreAuthorize("hasRole('MODERATOR')")
//    public String moderatorAccess() {
//        return "Moderator Board.";
//    }

//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//    @GetMapping("/user")
//    public String userAccess() {
//        return "User Content.";
//    }

}
