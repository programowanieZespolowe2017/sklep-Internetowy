package com.smcebi.checkout;

import javax.persistence.*;
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
    @OneToMany(cascade=CascadeType.ALL)
    private List<OrderEntry> products;


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
}
