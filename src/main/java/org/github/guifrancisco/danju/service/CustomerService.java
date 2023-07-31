package org.github.guifrancisco.danju.service;

import lombok.extern.slf4j.Slf4j;

import org.github.guifrancisco.danju.domain.dto.DataCustomer;
import org.github.guifrancisco.danju.domain.dto.DataRegisterCustomer;
import org.github.guifrancisco.danju.domain.dto.DataUpdateCustomer;
import org.github.guifrancisco.danju.domain.entity.Customer;
import org.github.guifrancisco.danju.repository.CustomerRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer registerCustomer(DataRegisterCustomer dataRegisterCustomer){
        log.info("[CustomerService.registerCustomer] - [Service]");
        return customerRepository.save(new Customer(dataRegisterCustomer));
    }

    public Customer updateCustomer(String id,DataUpdateCustomer dataUpdateCustomer){
        log.info("[CustomerService.updateCustomer] - [Service]");
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer with id " + id + " not found"));
        customer.updateDataCustomer(dataUpdateCustomer);
        return customerRepository.save(customer);
    }

    public void deleteCustomer(String id){
        log.info("[CustomerService.deleteCustomer] - [Service]");
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer with id " + id + " not found"));
        customerRepository.delete(customer);
    }

    public Page<DataCustomer> findAllCustomers(Pageable pageable){
        log.info("[CustomerService.findAllCustomers] - [Service]");
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers.map(DataCustomer::new);
    }




}
