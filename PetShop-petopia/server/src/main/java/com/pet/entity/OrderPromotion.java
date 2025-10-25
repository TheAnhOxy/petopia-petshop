package com.pet.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_promotions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderPromotion {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "order_promotion_id", length = 10, nullable = false)
    private String orderPromotionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "promotion_id", nullable = false)
    private Promotion promotion;

    @Column(name = "discount_applied", nullable = false)
    private Double discountApplied;
}