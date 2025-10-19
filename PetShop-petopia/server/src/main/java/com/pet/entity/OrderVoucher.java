package com.pet.entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_vouchers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderVoucher {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "order_voucher_id", length = 10, nullable = false)
    private String orderVoucherId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "voucher_id", nullable = false)
    private Voucher voucher;

    @Column(name = "discount_applied", nullable = false)
    private Double discountApplied;
}