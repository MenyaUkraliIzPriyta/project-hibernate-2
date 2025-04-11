package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.enums.Rating;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name= "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Long inventoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(precision = 5,scale = 2)
    private BigDecimal amount;
}
