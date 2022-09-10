package com.example.two.repository;


import com.example.two.models.Product;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

// List<Product> findProductsByTagsId(Long tagId);
//List<Product> findByPublished(boolean published);
//Product getProductByTitle(String title);
List<Product> findById(int id);
}
