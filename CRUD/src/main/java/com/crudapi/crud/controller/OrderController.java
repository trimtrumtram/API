package com.crudapi.crud.controller;

import com.crudapi.crud.dto.order.CreateOrderDTO;
import com.crudapi.crud.dto.order.OrderResponseDTO;
import com.crudapi.crud.dto.order.UpdateOrderDTO;
import com.crudapi.crud.repository.OrderRepository;
import com.crudapi.crud.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @PostMapping("/orders")
    public OrderResponseDTO createOrder(@RequestBody CreateOrderDTO dto) {
        return orderService.createOrder(dto);
    }

    @PutMapping("/order/{id}")
    public OrderResponseDTO updateOrder(@PathVariable Long id, @RequestBody UpdateOrderDTO dto) {
        return orderService.updateOrder(id, dto);
    }

    @DeleteMapping("/order/{id}")
    public boolean deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return true;
    }

    @GetMapping("/order/{id}")
    public OrderResponseDTO getOrderById(@PathVariable Long id) {
        return orderService.getOrder(id);
    }
}
