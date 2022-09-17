package com.example.two.services.serviceInterfaces;

import com.example.two.dto.inputs.CartItemInputDto;

public interface ShopService {
    void addToCart(CartItemInputDto cartIDto, Long userId);


//    void addToCart(ProductDto addToCartDto);
}
