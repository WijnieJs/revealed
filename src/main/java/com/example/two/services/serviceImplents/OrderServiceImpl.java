package com.example.two.services.serviceImplents;

import com.example.two.dto.ResponseDto;
import com.example.two.exceptions.ApiRequestException;
import com.example.two.exceptions.ProductNotFoundException;
import com.example.two.models.Cart;
import com.example.two.models.Order;
import com.example.two.models.Product;
import com.example.two.models.User;
import com.example.two.repository.OrderRepository;

import com.example.two.services.serviceInterfaces.OrderService;
import com.example.two.services.serviceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final UserService userService;


    public OrderServiceImpl(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }


    @Override
    public ResponseDto createNewOrder(String username) {

            try {
                User user = userService.findByUsername(username);
                Cart cart = user.getCart();
                List<Product> products = cart.getItems();
                Order newOrder = new Order();

                newOrder.setItems(cart.getItems().stream().collect(Collectors.toList()));
                newOrder.setTotal(cart.getTotal());
                newOrder.setUser(cart.getUser());
                orderRepository.save(newOrder);
                return new ResponseDto("success", "Order created");
            } catch (Exception e) {
                throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public List<Order> findOrdersFromUser(String username) {

        User user = userService.findByUsername(username);

        return orderRepository.findByUser(user);
    }


}


