package com.example.two.repository;

import com.example.two.models.Order;
import com.example.two.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);



}
