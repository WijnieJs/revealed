package com.example.two.dto;


import com.example.two.models.Product;

import javax.validation.constraints.NotNull;

public class ProductDto {

    private Integer id;
    private @NotNull String title;
    private String imageURL;
    private @NotNull double price;
    private boolean published;
    private @NotNull String description;


    public ProductDto(Product product) {

        this.setImageURL(product.getImageURL());
        this.setDescription(product.getDescription());
        this.setPrice(product.getPrice());

    }

    public ProductDto(@NotNull String title, @NotNull String imageURL, @NotNull double price, @NotNull String description ) {
        this.title = title;
        this.imageURL = imageURL;
        this.price = price;
        this.description = description;

    }

    public ProductDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
