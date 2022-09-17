package com.example.two.services.serviceImplents;

import com.example.two.dto.inputs.CartItemInputDto;
import com.example.two.repository.ProductRepository;
import com.example.two.repository.UserRepository;
import com.example.two.services.serviceInterfaces.ShopService;
import org.springframework.stereotype.Service;
import com.example.two.services.serviceInterfaces.ProductService;

@Service
public class ShopServiceImpl implements ShopService {

    private final ProductRepository productRepository;
    private final ProductService productService;
    private final UserRepository userRepository;

    public ShopServiceImpl(ProductRepository productRepository, ProductService productService, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.userRepository = userRepository;
    }


    public void addToCart(CartItemInputDto addToCartDto, Long userId) {



        // find the prodct
        // find the user
        // check if product is already in cart and update quantity
        // if not add product to cart

    }
}
