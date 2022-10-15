package com.example.two.controllers;


import com.example.two.dto.ProductDto;
import com.example.two.dto.ResponseDto;

import com.example.two.models.Order;
import com.example.two.services.serviceInterfaces.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public")
public class OrderController {


    @Autowired
    OrderService orderService;


    @PostMapping("/purchase/{username}")
    public ResponseEntity<ResponseDto> submit(@PathVariable String username, @RequestBody List<String> products) {

        ResponseDto response = orderService.createNewOrder(username, products);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/previousOrders/{username}")
    public ResponseEntity<List<Order>> getOrdersForUser(@PathVariable String username) {

            List<Order> body = orderService.findOrdersFromUser(username);

        if (body.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Order>>(body, HttpStatus.OK);

    }

}
