package com.pet.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "services")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Service {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "service_id", nullable = false)
    private String serviceId;

    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "image_url")
    private String imageUrl;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "service", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = false)
    @ToString.Exclude
    private Set<BookingService> bookingServices;
}