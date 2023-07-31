package org.github.guifrancisco.danju.domain.dto;

import org.github.guifrancisco.danju.domain.entity.Customer;

public record DataCustomer(
        String id,
        String name,
        String telephone,
        String address
) {
    public DataCustomer(Customer customer){
        this(customer.getId(), customer.getName(), customer.getTelephone(), customer.getAddress());
    }

}
