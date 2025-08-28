package com.pet.entity;



import lombok.*;
import com.pet.enums.VoucherDiscountType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "vouchers", indexes = {@Index(name = "idx_code", columnList = "code")})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Voucher {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "voucher_id", length = 10, nullable = false)
    private String voucherId;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type", nullable = false, columnDefinition = "ENUM('PERCENTAGE','FIXED_AMOUNT')")
    private VoucherDiscountType discountType;

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

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "voucher", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},  fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<OrderVoucher> orderVouchers;
}