package dev.michaelcao512.revshop_springboot.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private double amount;

//    TODO: other fields for payment
}
