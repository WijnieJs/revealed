package com.example.two.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column
    private Long  id;

    @ManyToMany
    @JsonProperty
    @Column
    private List<Product> items;

    @OneToOne(mappedBy = "cart")
    @JsonProperty
    private User user;

    @Column
    @JsonProperty
    private BigDecimal total;


    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public void addItem(Product item) {
        if(items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
        if(total == null) {
            total = new BigDecimal(0);
        }

        total = total.add(item.getPrice());
    }

    public void removeItem(Product item) {
        if(items == null) {
            items = new ArrayList<>();
        }
        items.remove(item);
        if(total == null) {
            total = new BigDecimal(0);
        }
        total = total.subtract(item.getPrice());
    }
}
