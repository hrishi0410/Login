package com.courseEnroll.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentPublicDTO {

    private BigDecimal amount;
    private String status;        // e.g. SUCCESS / PENDING
    private LocalDateTime paymentDate;
    private String method;        // Optional: "UPI", "Card", etc.
}