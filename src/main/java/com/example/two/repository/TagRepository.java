package com.example.two.repository;

import com.example.two.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository <Tag, Long> {
    List<Tag>  findTagsByProductsId(Long productId);

}
