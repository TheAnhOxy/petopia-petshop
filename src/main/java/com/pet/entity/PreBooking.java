package com.pet.entity;


import lombok.*;
import com.pet.enums.BookingStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pre_bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PreBooking {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "booking_id", length = 10, nullable = false)
    private String bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @Column(name = "deposit_amount", nullable = false)
    private Double depositAmount;

    @Column(name = "expected_date")
    private LocalDate expectedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('PENDING','CONFIRMED','CANCELLED') DEFAULT 'PENDING'")
    private BookingStatus status;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)")
    private LocalDateTime updatedAt;
}