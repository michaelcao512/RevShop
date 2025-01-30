package dev.michaelcao512.revshop_springboot.Controllers;

import dev.michaelcao512.revshop_springboot.DTO.OrderDto;
import dev.michaelcao512.revshop_springboot.Entities.Order;
import dev.michaelcao512.revshop_springboot.Services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

//    gets all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

//    get all orders by buyer id
    @GetMapping("/buyer/{userId}")
    public List<Order> getOrdersByBuyerId(@PathVariable Long userId) {
        return orderService.getOrderByBuyer(userId);
    }

//    get all orders by seller id
    @GetMapping("/seller/{userId}")
    public List<Order> getOrdersBySellerId(@PathVariable Long userId) {
        return orderService.getOrderBySeller(userId);
    }

//    creates an order given a buyer id, cart id, shipping address,  billing address, and paymentDto
    @PostMapping
    public Order createOrder(@RequestBody OrderDto request) {
        return orderService.createOrder(request);
    }





}
