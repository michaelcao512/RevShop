package dev.michaelcao512.revshop_springboot.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference(value = "seller-products")
    private List<Product> products;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference(value = "buyer-carts")
    private List<Cart> carts;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference(value = "buyer-orders")
    private List<Order> orders;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference(value = "buyer-reviews")
    private List<Review> reviews;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference(value = "buyer-favorites")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Notification> notifications;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + userType);
    }


    public enum UserType {
        BUYER,
        SELLER
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) o;
        return getUserId() != null && Objects.equals(getUserId(), user.getUserId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
