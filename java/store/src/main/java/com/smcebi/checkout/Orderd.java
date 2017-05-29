package com.smcebi.checkout;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * 5/1/2017 1:26 PM
 */
@Entity
public class Orderd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String address;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderEntry> products;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Timestamp timestamp;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderEntry> getProducts() {
        return products;
    }

    public void setProducts(List<OrderEntry> products) {
        this.products = products;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
