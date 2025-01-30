package dev.michaelcao512.revshop_springboot.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    @JsonBackReference(value = "seller-products")
    private User seller;

    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private double price;
    private double discountedPrice;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @ToString.Exclude
    @JsonManagedReference(value = "product-categories")
    private List<Category> categories;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference(value = "product-cartItems")
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference(value = "product-orderItems")
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference(value = "product-reviews")
    private List<Review> reviews;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference(value = "product-favorites")
    private List<Favorite> favorites;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonManagedReference(value = "product-inventory")
    private Inventory inventory;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Product product = (Product) o;
        return getProductId() != null && Objects.equals(getProductId(), product.getProductId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
