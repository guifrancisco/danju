package org.github.guifrancisco.danju.service;

import lombok.extern.slf4j.Slf4j;
import org.github.guifrancisco.danju.domain.dto.*;
import org.github.guifrancisco.danju.domain.entity.Customer;
import org.github.guifrancisco.danju.domain.entity.Order;
import org.github.guifrancisco.danju.domain.entity.Product;
import org.github.guifrancisco.danju.domain.enums.OrderStatus;
import org.github.guifrancisco.danju.infra.mapper.OrderMapper;
import org.github.guifrancisco.danju.infra.repository.CustomerRepository;
import org.github.guifrancisco.danju.infra.repository.OrderRepository;
import org.github.guifrancisco.danju.infra.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final CustomerRepository customerRepository;

    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, CustomerRepository customerRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.orderMapper = orderMapper;
    }

    public void registerOrder(DataRegisterOrder dataRegisterOrder){
        log.info("[OrderService.registerOrder] - [Service]");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Customer customer = customerRepository.findById(dataRegisterOrder.customerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer with id " + dataRegisterOrder.customerId() + "not found"));

        List<String> productIds = dataRegisterOrder.orderLines().stream()
                .map(DataRegisterOrderLine::productId)
                .toList();

        List<Product> products = productRepository.findAllById(productIds);

        double totalValue = dataRegisterOrder.orderLines().stream()
                .mapToDouble(dataOrderLine -> {
                    Product product = products.stream().filter(p -> p.getId().equals(dataOrderLine.productId())).findFirst().orElse(null);
                    return product != null ? product.getPrice() * dataOrderLine.quantity() : 0;
                })
                .sum();

        Order order = orderMapper.toEntity(dataRegisterOrder, customer, products);
        order.setTotalValue(totalValue);
        order.setCreatedBy(username);

        orderRepository.save(order);
    }

    public void updateOrder(String id,DataUpdateOrder dataUpdateOrder){
        log.info("[OrderService.updateOder] - [Service]");
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order with id " + id + "not found"));
        orderMapper.updateEntityFromDto(order, dataUpdateOrder);
        orderRepository.save(order);

    }

    public void deleteOrder(String id){
        log.info("[OrderService.deleteOrder] - [Service]");
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order with id " + id + "not found"));
        orderRepository.delete(order);
    }

    public Page<DataOrder> findAllOrders(Pageable pageable){
        log.info("[OrderService.findAllOrders] - [Service]");
        Page<Order> orders = orderRepository.findAll(pageable);
        return orders.map(orderMapper::toDto);
    }


}
