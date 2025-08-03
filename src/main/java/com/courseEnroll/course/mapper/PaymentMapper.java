package com.courseEnroll.course.mapper;

import com.courseEnroll.course.model.enums.PaymentStatus;
import org.springframework.stereotype.Component;
import com.courseEnroll.course.dto.*;
import com.courseEnroll.course.model.Payment;

import java.time.LocalDateTime;

@Component
public class PaymentMapper {

    public PaymentPublicDTO toPublicDTO(Payment payment) {
        if (payment == null) return null;

        return new PaymentPublicDTO(
                payment.getAmount(),
                payment.getStatus(),
                payment.getPaymentDate(),
                payment.getMethod()

        );
    }
    public PaymentAdminDTO toAdminDTO(Payment payment) {
        if (payment == null) return null;

        return new PaymentAdminDTO(
                payment.getId(),
                payment.getPaymentId(),
                payment.getAmount(),
                payment.getMethod(),
                payment.getPaymentMethod(),
                payment.isVerified(),
                payment.getStatus(),
                payment.getPaymentDate(),
                payment.getPaymentGatewayId(),
                payment.getPayerName(),
                payment.getEnrollment() != null ? payment.getEnrollment().getId() : null,
                payment.getStudent() != null ? payment.getStudent().getId() : null
        );
    }
    public Payment toEntity(CreatePaymentRequestDTO dto) {
        if (dto == null) return null;

        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());
        payment.setMethod(dto.getMethod());
        payment.setPaymentId(dto.getPaymentId());
        payment.setPaymentGatewayId(dto.getPaymentGatewayId());
        payment.setPayerName(dto.getPayerName());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setStatus(dto.getStatus().name()); // Converts enum to String // Enum passed directly

        // Future-ready placeholders:
        // payment.setEnrollment(enrollmentService.findById(dto.getEnrollmentId()));
        // payment.setStudent(studentService.findById(dto.getStudentId()));

        // Set additional fields if required (e.g., enrollment, student)

        return payment;
    }

    // We’ll add other methods here later (toAdminDTO, toEntity, etc.)
}
