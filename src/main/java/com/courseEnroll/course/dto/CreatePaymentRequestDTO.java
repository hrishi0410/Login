package com.courseEnroll.course.dto;

import com.courseEnroll.course.model.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreatePaymentRequestDTO {
    private BigDecimal amount;
    private String payerName;
    private String method;
    private Long enrollmentId;
    private Long studentId;
    private String paymentId;
    private String paymentGatewayId;
    private PaymentStatus status;
    private LocalDateTime paymentDate; // Use LocalDateTime as expected by your entity

}