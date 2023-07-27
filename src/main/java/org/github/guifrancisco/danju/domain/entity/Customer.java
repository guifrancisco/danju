package org.github.guifrancisco.danju.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "customer")
@Table(name = "customer")
@Getter
@NoArgsConstructor
public class Customer {

    @Id
    private String id;
    private String name;
    private String telephone;
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order>orders;




}
