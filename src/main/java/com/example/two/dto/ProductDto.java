package com.example.two.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


public class ProductDto {

    private int id;
    private @NotNull String title;
    private String imageURL;
    private  double price;
    private boolean published;
    @Size(min = 2, message = "Description should have at least 2 characters")
    private @NotNull String descriptionDt;


    private List<String> tags;

    public ProductDto() {
    }

    public ProductDto(int id, @NotNull String title, String imageURL, double price, boolean published, @NotNull String description, List<String> tags) {
        this.id = id;
        this.title = title;
        this.imageURL = imageURL;
        this.price = price;
        this.published = published;
        this.descriptionDt = description;
        this.tags = tags;
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

    public String getDescriptionDt() {
        return descriptionDt;
    }

    public void setDescription(String description) {
        this.descriptionDt= description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
