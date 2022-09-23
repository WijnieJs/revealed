package com.example.two.repository;

import com.example.two.models.Cart;
import com.example.two.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUser(User user);

}
