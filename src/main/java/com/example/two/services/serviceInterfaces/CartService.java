package com.example.two.services.serviceInterfaces;

import com.example.two.dto.ResponseDto;
import com.example.two.dto.inputs.CartItemInputDto;

public interface CartService {

    ResponseDto addTocart(CartItemInputDto addTooAdd);

    ResponseDto removeFromCart(CartItemInputDto addTooAdd);


//    void addToCart(ProductDto addToCartDto);
}
