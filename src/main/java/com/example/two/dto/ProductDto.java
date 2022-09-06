package com.example.two.dto;


import com.example.two.models.Product;
import com.example.two.models.Tag;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ProductDto {

    private Long id;
    private  String title;
    private String imageURL;
    private  double price;
    private boolean published;
    private  String description;


    private Set<Tag> tags = new HashSet<>();


    public ProductDto(  String imageURL,  String description,  boolean published,  double price) {
        this.imageURL = imageURL;
        this.published = published;
        this.price = price;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public ProductDto( String title,  double price,  String description, boolean published, Map tags ) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.published = published;
    }

    public ProductDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
