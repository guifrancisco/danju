package org.github.guifrancisco.danju.mapper;

import org.github.guifrancisco.danju.domain.dto.DataCustomer;
import org.github.guifrancisco.danju.domain.dto.DataRegisterCustomer;
import org.github.guifrancisco.danju.domain.dto.DataUpdateCustomer;
import org.github.guifrancisco.danju.domain.entity.Customer;
import org.springframework.stereotype.Component;


import java.util.UUID;
@Component
public class CustomerMapper {

    public Customer toEntity(DataRegisterCustomer dataRegisterCustomer){
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID().toString());
        customer.setName(dataRegisterCustomer.name());
        customer.setTelephone(dataRegisterCustomer.telephone());
        customer.setAddress(dataRegisterCustomer.address());
        return customer;
    }

    public DataCustomer toDto(Customer customer){
        return new DataCustomer(
                customer.getId(),
                customer.getName(),
                customer.getTelephone(),
                customer.getAddress()
        );
    }

    public void updateEntityFromDto(Customer customer, DataUpdateCustomer dataUpdateCustomer){
        if(dataUpdateCustomer.name() != null){
            customer.setName(dataUpdateCustomer.name());
        }
        if(dataUpdateCustomer.telephone() != null){
            customer.setTelephone(dataUpdateCustomer.telephone());
        }
        if(dataUpdateCustomer.address() != null){
            customer.setAddress(dataUpdateCustomer.address());
        }
    }
}
