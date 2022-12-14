package com.example.two.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    @Column(name = "title")
    private @NotNull String title;

    @Size(min = 2, message = "Description should have at least 2 characters")
    @Column(name = "description")
    private @NotNull  String description;

    @Column(name = "published")
    private boolean published;

    private BigDecimal price;

    @OneToOne
    FileUploadResponse file;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "product_tags",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") })
        List<Tag> tags;
//    private Set<Tag> tags = new HashSet<>();


    public Product() {
    }

    public Product(String title, String description, boolean published, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.published = published;
        this.price = price;
    }
    public void setFile(FileUploadResponse file) {
        this.file = file;
    }
    public FileUploadResponse getFile() {
        return file;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }



    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean isPublished) {
        this.published = isPublished;
    }


public List<Tag> getTags() {
    return tags;
}

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }



    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getProducts().add(this);
    }

    public void removeTag(long tagId) {
        Tag tag = this.tags.stream().filter(t -> t.getId() == tagId).findFirst().orElse(null);
        if (tag != null) {
            this.tags.remove(tag);
            tag.getProducts().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
    }



}