package com.pet.entity;


import com.pet.enums.PromotionType;
import com.pet.enums.PromotionVoucherStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "promotions", indexes = {@Index(name = "idx_code", columnList = "code")})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Promotion {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "promotion_id", length = 10, nullable = false)
    private String promotionId;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "promotion_type", nullable = false, columnDefinition = "ENUM('FREESHIP','DISCOUNT','CASHBACK','BUNDLE')")
    private PromotionType promotionType;

    @Column(name = "discount_value", nullable = false)
    private Double discountValue;

    @Column(name = "min_order_amount")
    private Double minOrderAmount;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "max_usage")
    private Integer maxUsage;

    @Column(name = "used_count", columnDefinition = "INT DEFAULT 0")
    private Integer usedCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "ENUM('ACTIVE','INACTIVE') DEFAULT 'ACTIVE'")
    private PromotionVoucherStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "image_url")
    private String imageUrl;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "promotion", cascade = {CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH},  fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<OrderPromotion> orderPromotions;
}