package com.example.two.dto;


import com.example.two.models.Product;
import com.example.two.models.Tag;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ProductDto {

    private Long id;
    private @NotNull String title;
    private String imageURL;
    private @NotNull double price;
    private boolean published;
    private @NotNull String description;


    private Set<Tag> tags = new HashSet<>();

    public ProductDto(Product product) {
        this.setId(product.getId());
        this.setTitle(product.getTitle());
        this.setImageURL(product.getImageURL());
        this.setPrice(product.getPrice());
        this.setPublished(product.isPublished());
        this.setDescription(product.getDescription());
        this.setTags(product.getTags());
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public ProductDto(@NotNull String title, @NotNull String imageURL, @NotNull double price, @NotNull String description, Map tags ) {
        this.title = title;
        this.imageURL = imageURL;
        this.price = price;
        this.description = description;

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
