package com.pet.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "loyalty_transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LoyaltyTransaction {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "transaction_id", length = 10, nullable = false)
    private String transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "points_earned", columnDefinition = "INT DEFAULT 0")
    private Integer pointsEarned;

    @Column(name = "points_used", columnDefinition = "INT DEFAULT 0")
    private Integer pointsUsed;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)", updatable = false)
    private LocalDateTime createdAt;
}