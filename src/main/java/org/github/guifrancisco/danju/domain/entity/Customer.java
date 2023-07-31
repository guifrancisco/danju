package org.github.guifrancisco.danju.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.github.guifrancisco.danju.domain.dto.DataRegisterCustomer;
import org.github.guifrancisco.danju.domain.dto.DataUpdateCustomer;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Customer {

    @Id
    private String id;
    private String name;
    private String telephone;
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order>orders;

    public Customer(DataRegisterCustomer dataRegisterCustomer){
        this.id = UUID.randomUUID().toString();
        this.name = dataRegisterCustomer.name();
        this.telephone = dataRegisterCustomer.telephone();
        this.address = dataRegisterCustomer.address();
    }

    public void updateDataCustomer(DataUpdateCustomer dataUpdateCustomer){
        if(dataUpdateCustomer.name() != null){
            this.name = dataUpdateCustomer.name();
        }
        if(dataUpdateCustomer.telephone() != null){
            this.telephone = dataUpdateCustomer.telephone();
        }
        if(dataUpdateCustomer.address() != null){
            this.address = dataUpdateCustomer.address();
        }
        if(dataUpdateCustomer.name() == null && dataUpdateCustomer.telephone() == null &&
                dataUpdateCustomer.address() == null){
            throw new IllegalStateException("You must provide at least one value for the update.");
        }
    }





}
