package com.example.two.services.serviceImplents;

import com.example.two.dto.ResponseDto;
import com.example.two.dto.inputs.CartItemInputDto;
import com.example.two.exceptions.ProductNotFoundException;
import com.example.two.exceptions.UserIdException;
import com.example.two.models.Cart;
import com.example.two.models.Product;
import com.example.two.models.User;

import com.example.two.repository.CartRepository;
import com.example.two.services.serviceInterfaces.CartService;
import com.example.two.services.serviceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.two.services.serviceInterfaces.ProductService;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class CartServiceImpl implements CartService {

     @Autowired
    private final ProductService productService;

    @Autowired
    private final UserService userService;

    @Autowired
    CartRepository cartRepository;

    public CartServiceImpl(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }




    @Override
    public ResponseDto addTocart(CartItemInputDto addTooAdd) {
        User user = userService.findByUsername(addTooAdd.getUsername());
        Cart cart = user.getCart();
        Product product = productService.findInDbProductById(addTooAdd.getItemId());
//                IntStream.range(0, addTooAdd.getQuantity())
//                .forEach(i -> cart.addItem(product));
//
//            cart.setQuantity();

        Stream<Integer> numbers = Stream.of(1,2,3,4,5);
        Optional<Integer> intOptional = numbers.reduce((i, j) -> {return i*j;});
        //120
        intOptional.ifPresent(integer -> System.out.println("Multiplication = " + integer));
        cartRepository.save(cart);

        return new ResponseDto("success", "Product added to your cart");
    }

    @Override
    public ResponseDto removeFromCart(CartItemInputDto addTooAdd) {

        User user = getUserByName(addTooAdd.getUsername());


        Product productToRemove = productService.findInDbProductById(addTooAdd.getItemId());
       Cart cart = user.getCart();

        IntStream.range(0, addTooAdd.getQuantity())
                .forEach(i -> cart.removeItem(productToRemove));

        cartRepository.save(cart);

        return new ResponseDto("success", "Product removed from your cart");

    }

    public User getUserByName(String username) {
        try {
            return userService.findByUsername(username);

        } catch (Exception e) {
            throw new UserIdException("Could not find user with username");
        }

    }

    public void hasProductInCart(long productInCart) {
        try {
            productService.findInDbProductById(productInCart);

        } catch (Exception e) {
            throw new ProductNotFoundException("Item was not in cart, dont worry! we Gotchuuu ");

        }
    }

}
