package com.pet.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "booking_services")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BookingService {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "booking_service_id", length = 10, nullable = false)
    private String bookingServiceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "appointment_date", nullable = true)
    private Date appointmentDate;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "quantity", nullable = false, columnDefinition = "INT DEFAULT 1")
    private Integer quantity;

    @Column(name = "price_at_purchase", nullable = false)
    private Double priceAtPurchase;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)")
    private LocalDateTime updatedAt;
}