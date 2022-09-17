package com.example.two.repository;

import com.example.two.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Cart, Integer> {
}
