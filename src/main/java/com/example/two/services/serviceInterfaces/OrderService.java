package com.example.two.services.serviceInterfaces;


import com.example.two.dto.ProductDto;
import com.example.two.dto.ResponseDto;
import com.example.two.models.Order;

import java.util.List;

public interface OrderService {
    ResponseDto createNewOrder(String username,  List<String> products);

   List<Order> findOrdersFromUser(String username);
}
