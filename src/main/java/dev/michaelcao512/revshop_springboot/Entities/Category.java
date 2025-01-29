package dev.michaelcao512.revshop_springboot.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToMany(mappedBy = "categories")
    private List<Product> products;
}
