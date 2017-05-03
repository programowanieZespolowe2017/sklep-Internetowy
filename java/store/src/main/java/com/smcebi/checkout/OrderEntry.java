package com.smcebi.checkout;

import com.smcebi.products.Product;

import javax.persistence.*;

/**
 * 5/1/2017 1:24 PM
 */
@Entity
public class OrderEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long qty;

    @OneToOne(cascade=CascadeType.PERSIST)
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderEntry(Long qty, Product product) {
        this.qty = qty;
        this.product = product;
    }

    public OrderEntry() {
    }
}
