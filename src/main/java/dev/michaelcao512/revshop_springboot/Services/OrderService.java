package dev.michaelcao512.revshop_springboot.Services;

import dev.michaelcao512.revshop_springboot.DTO.OrderDto;
import dev.michaelcao512.revshop_springboot.Entities.*;
import dev.michaelcao512.revshop_springboot.Repositories.CartRepository;
import dev.michaelcao512.revshop_springboot.Repositories.OrderRepository;
import dev.michaelcao512.revshop_springboot.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final InventoryService inventoryService;
    private final PaymentService paymentService;

    public OrderService(OrderRepository orderRepository, CartRepository cartRepository, UserRepository userRepository, InventoryService inventoryService, PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.inventoryService = inventoryService;
        this.paymentService = paymentService;
    }

    public Order createOrder(OrderDto request) {
        User buyer = userRepository.findById(request.buyerUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findById(request.cartId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Order order = new Order();
        order.setBuyer(buyer);
        order.setShippingAddress(request.shippingAddress());
        order.setBillingAddress(request.billingAddress());
        order.setStatus(Order.OrderStatus.PENDING);
        double total = 0.0;

        order.setOrderItems(new ArrayList<>());
        for (CartItem item : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getProduct().getPrice());
            order.getOrderItems().add(orderItem);

            inventoryService.updateInventory(item.getProduct().getProductId(), item.getQuantity());

            total += item.getProduct().getPrice() * item.getQuantity();
        }
        order.setTotal(total);

        Order savedOrder = orderRepository.save(order);

        Payment payment = paymentService.processPayment(savedOrder, request.paymentDto());

        if (payment.getStatus() == Payment.PaymentStatus.CANCELLED) {
            order.setStatus(Order.OrderStatus.CANCELLED);
            orderRepository.save(order);
            throw new RuntimeException("Payment failed");
        } else {
            order.setStatus(Order.OrderStatus.PROCESSED);
        }

        cartRepository.delete(cart);

        return orderRepository.save(order);
    }



    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    public List<Order> getOrderByBuyer(Long userId) {
        return orderRepository.findByBuyerUserId(userId);
    }

    public List<Order> getOrderBySeller(Long userId) {
        return orderRepository.findBySellerUserId(userId);
    }
}
