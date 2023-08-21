package org.github.guifrancisco.danju.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.github.guifrancisco.danju.domain.dto.DataOrder;
import org.github.guifrancisco.danju.domain.dto.DataRegisterOrder;
import org.github.guifrancisco.danju.domain.dto.DataUpdateOrder;
import org.github.guifrancisco.danju.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("v1/order")
@RestController
@Slf4j
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> registerOrder(@RequestBody @Valid DataRegisterOrder dataRegisterOrder){
        log.info("[OrderController.registerOrder] - [Controller]");
        orderService.registerOrder(dataRegisterOrder);
        return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable String id, @RequestBody @Valid DataUpdateOrder dataUpdateOrder){
        log.info("[OrderController.updateOrder] - [Controller]");
        orderService.updateOrder(id, dataUpdateOrder);
        return ResponseEntity.ok("Order updated successfully");
    }

    @GetMapping
    public ResponseEntity<Page<DataOrder>> findAllOrders(@PageableDefault(size = 10) Pageable pageable){
        log.info("[OrderController.findAllOrders] - [Controller]");
        Page<DataOrder> orders = orderService.findAllOrders(pageable);
        return ResponseEntity.ok().body(orders);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable String id){
        log.info("[OrderController.deleteOrder] - [Controller]");
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
