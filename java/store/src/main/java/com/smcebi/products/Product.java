package com.smcebi.products;

import javax.persistence.*;

/**
 * 4/30/2017 12:11 AM
 */

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int stockLevel;
    private double price;
    private String name;
    private String description;
    private String img;

    @ManyToOne(cascade={CascadeType.REFRESH})
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Product(int stockLevel, double price, String name, String description, String img, Category category) {
        this.stockLevel = stockLevel;
        this.price = price;
        this.name = name;
        this.description = description;
        this.img = img;
        this.category = category;
    }

    public Product() {
    }
}
