package dev.michaelcao512.revshop_springboot.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long inventoryId;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer threshold;
}
