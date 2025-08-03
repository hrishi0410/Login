package com.courseEnroll.course.service;

import com.courseEnroll.course.dto.PaymentAdminDTO;
import com.courseEnroll.course.model.Payment;

import java.util.List;

public interface PaymentService {
    Payment createPayment(Payment payment);
    Payment getPaymentById(Long id);
    List<Payment> getAllPayments();
    void deletePayment(Long id);
    boolean verifyPayment(String paymentId, String signature);
    PaymentAdminDTO getAdminPayment(Long id);
}