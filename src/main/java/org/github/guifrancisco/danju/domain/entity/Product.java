package org.github.guifrancisco.danju.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class Product {

    @Id
    private String id;

    private String name;
    private double price;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

}
