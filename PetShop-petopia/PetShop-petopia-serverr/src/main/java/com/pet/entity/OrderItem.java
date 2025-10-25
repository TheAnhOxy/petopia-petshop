package com.pet.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderItem {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "order_item_id", length = 10, nullable = false)
    private String orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @Column(name = "quantity", nullable = false, columnDefinition = "INT DEFAULT 1")
    private Integer quantity;

    @Column(name = "price_at_purchase", nullable = false)
    private Double priceAtPurchase;

    @Column(name = "discount_applied", columnDefinition = "DOUBLE DEFAULT 0")
    private Double discountApplied;
}
