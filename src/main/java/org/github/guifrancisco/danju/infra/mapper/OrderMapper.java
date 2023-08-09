package org.github.guifrancisco.danju.infra.mapper;

import org.github.guifrancisco.danju.domain.dto.DataOrder;
import org.github.guifrancisco.danju.domain.dto.DataRegisterOrder;
import org.github.guifrancisco.danju.domain.dto.DataRegisterOrderLine;
import org.github.guifrancisco.danju.domain.dto.DataUpdateOrder;
import org.github.guifrancisco.danju.domain.entity.Customer;
import org.github.guifrancisco.danju.domain.entity.Order;
import org.github.guifrancisco.danju.domain.entity.OrderLine;
import org.github.guifrancisco.danju.domain.entity.Product;
import org.github.guifrancisco.danju.domain.enums.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public Order toEntity(DataRegisterOrder dataRegisterOrder, Customer customer, List<Product> products) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setCustomer(customer);
        order.setDeliveryDate(dataRegisterOrder.deliveryDate());
        order.setTotalValue(dataRegisterOrder.totalValue());
        order.setStatus(convertToOrderStatus(dataRegisterOrder.status()));
        order.setOrderLines(toOrderLines(dataRegisterOrder.orderLines(), order, products));
        return order;
    }

    public DataOrder toDto(Order order) {
        return new DataOrder(order);
    }

    private List<OrderLine> toOrderLines(List<DataRegisterOrderLine> dataOrderLines, Order order, List<Product> products) {
        return dataOrderLines.stream()
                .map(dataOrderLine -> {
                    OrderLine orderLine = new OrderLine();
                    orderLine.setId(UUID.randomUUID().toString());
                    orderLine.setOrder(order);
                    Product product = products.stream().filter(p -> p.getId().equals(dataOrderLine.productId())).findFirst().orElse(null);
                    orderLine.setProduct(product);
                    orderLine.setQuantity(dataOrderLine.quantity());
                    return orderLine;
                })
                .collect(Collectors.toList());
    }

    private OrderStatus convertToOrderStatus(String status) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.getStatus().equalsIgnoreCase(status)) {
                return orderStatus;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + status);
    }

    public void updateEntityFromDto(Order order, DataUpdateOrder dataUpdateOrder){
        if(dataUpdateOrder.deliveryDate() != null){
            order.setDeliveryDate(dataUpdateOrder.deliveryDate());
        }
        if(dataUpdateOrder.status() != null){
            order.setStatus(convertToOrderStatus(dataUpdateOrder.status()));
        }
        if(dataUpdateOrder.paymentType() != null){
            order.setPaymentType(dataUpdateOrder.paymentType());
        }
    }
}
