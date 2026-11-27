package com.example.order_service.service;

import com.example.order_service.entity.Order;
import com.example.order_service.entity.OrderItem;
import com.example.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> getById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    public List<Order> getByCustomerPhone(String phone) {
        return orderRepository.findByCustomerPhone(phone);
    }

    @Transactional
    public Order create(Order order) {
        if (order.getOrderDate() == null) {
            order.setOrderDate(LocalDateTime.now());
        }
        if (order.getStatus() == null || order.getStatus().isBlank()) {
            order.setStatus("PENDING");
        }

        BigDecimal calculatedTotal = BigDecimal.ZERO;

        if (order.getItems() != null && !order.getItems().isEmpty()) {
            for (OrderItem item : order.getItems()) {
                item.setOrder(order);
                if (item.getPrice() != null && item.getQuantity() != null) {
                    BigDecimal subtotal = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
                    item.setSubtotal(subtotal);
                    calculatedTotal = calculatedTotal.add(subtotal);
                }
            }
        }

        if (order.getTotalAmount() == null || order.getTotalAmount().compareTo(BigDecimal.ZERO) <= 0) {
            order.setTotalAmount(calculatedTotal);
        }

        return orderRepository.save(order);
    }

    @Transactional
    public Optional<Order> update(Long id, Order orderDetails) {
        return orderRepository.findById(id).map(existing -> {
            existing.setCustomerName(orderDetails.getCustomerName());
            existing.setCustomerPhone(orderDetails.getCustomerPhone());
            existing.setShippingAddress(orderDetails.getShippingAddress());
            if (orderDetails.getPaymentMethod() != null) {
                existing.setPaymentMethod(orderDetails.getPaymentMethod());
            }
            if (orderDetails.getStatus() != null) {
                existing.setStatus(orderDetails.getStatus());
            }
            if (orderDetails.getNotes() != null) {
                existing.setNotes(orderDetails.getNotes());
            }
            return orderRepository.save(existing);
        });
    }

    @Transactional
    public Optional<Order> updateStatus(Long id, String newStatus) {
        return orderRepository.findById(id).map(existing -> {
            existing.setStatus(newStatus);
            return orderRepository.save(existing);
        });
    }

    @Transactional
    public boolean delete(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
