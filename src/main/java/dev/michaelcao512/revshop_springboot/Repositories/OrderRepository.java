package dev.michaelcao512.revshop_springboot.Repositories;

import dev.michaelcao512.revshop_springboot.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}