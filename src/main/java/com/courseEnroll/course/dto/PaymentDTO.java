package com.courseEnroll.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private Long id;
    private String paymentId;
    private BigDecimal amount;
    private String method;
    private String paymentMethod;
    private boolean verified;
    private String status;
    private LocalDateTime paymentDate;
    private String paymentGatewayId;

    private String payerName;

    // Reference by IDs (or nested DTOs later if needed)
    private Long enrollmentId;
    private Long studentId;
}