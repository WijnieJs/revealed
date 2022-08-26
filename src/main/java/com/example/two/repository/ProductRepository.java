package com.example.two.repository;

import com.example.two.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository <Product, Long> {

    List<Product> findProductsByTagsId(Long tagId);

}
