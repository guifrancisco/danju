package org.github.guifrancisco.danju.controller;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.github.guifrancisco.danju.domain.dto.DataCustomer;
import org.github.guifrancisco.danju.domain.dto.DataRegisterCustomer;
import org.github.guifrancisco.danju.domain.dto.DataUpdateCustomer;
import org.github.guifrancisco.danju.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("v1/customer")
@RestController
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> registerCustomer(@RequestBody DataRegisterCustomer dataRegisterCustomer) {
        log.info("[CustomerController.registerCustomer] - [Controller]");
        customerService.registerCustomer(dataRegisterCustomer);
        return new ResponseEntity<>("Customer created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable String id, @RequestBody DataUpdateCustomer dataUpdateCustomer){
        log.info("[CustomerController.updateCustomer] - [Controller]");
        customerService.updateCustomer(id ,dataUpdateCustomer);
        return ResponseEntity.ok("Employee updated successfully");
    }

    @GetMapping
    public ResponseEntity<Page<DataCustomer>> findAllCustomers(@PageableDefault(size = 10) Pageable pageable){
        log.info("[CustomerController.findAllCustomers] - [Controller]");
        Page<DataCustomer> customers = customerService.findAllCustomers(pageable);
        return ResponseEntity.ok().body(customers);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteCustomer(@PathVariable String id){
        log.info("[CustomerController.deleteCustomer] - [Controller]");
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }


}

