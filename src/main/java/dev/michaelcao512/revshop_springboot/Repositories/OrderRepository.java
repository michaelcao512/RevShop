package dev.michaelcao512.revshop_springboot.Repositories;

import dev.michaelcao512.revshop_springboot.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByBuyerUserId(Long userId);

    @Query("SELECT o FROM Order o JOIN o.orderItems oi JOIN oi.product p WHERE p.seller.userId = :userId")
    List<Order> findBySellerUserId(Long userId);
}