package com.pet.entity;


import lombok.*;
import com.pet.enums.DeliveryStatus;
import com.pet.enums.ShippingMethod;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "deliveries", indexes = {@Index(name = "idx_tracking_number", columnList = "tracking_number")})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Delivery {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "delivery_id", length = 10, nullable = false)
    private String deliveryId;

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "order_id", unique = true, nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "provider_id")
    private DeliveryProvider provider;

    @Column(name = "tracking_number", unique = true, length = 50)
    private String trackingNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "shipping_method", nullable = false, columnDefinition = "ENUM('STANDARD','EXPRESS','SAME_DAY','PICKUP')")
    private ShippingMethod shippingMethod;

    @Column(name = "shipping_fee", nullable = false)
    private Double shippingFee;

    @Column(name = "estimated_delivery_date", columnDefinition = "DATETIME(6)")
    private LocalDateTime estimatedDeliveryDate;

    @Column(name = "actual_delivery_date", columnDefinition = "DATETIME(6)")
    private LocalDateTime actualDeliveryDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status", columnDefinition = "ENUM('PREPARING','SHIPPED','IN_TRANSIT','DELIVERED','RETURNED','FAILED') DEFAULT 'PREPARING'")
    private DeliveryStatus deliveryStatus;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<DeliveryHistory> history;
}