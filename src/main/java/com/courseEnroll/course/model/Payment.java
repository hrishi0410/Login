package com.courseEnroll.course.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Razorpay or custom identifier
    private String paymentId;

    // Financials
    private BigDecimal amount;
    private String method;             // e.g., UPI, Card, NetBanking
    private String paymentMethod;      // Redundant? You can consolidate with 'method' if needed
    private boolean verified;
    private String status;             // e.g., SUCCESS, PENDING, FAILED
    private LocalDateTime paymentDate;
    private String paymentGatewayId;

    // Payer details
    private String payerName;

    @OneToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}