package com.courseEnroll.course.controller;

import com.courseEnroll.course.dto.PaymentAdminDTO;
import com.courseEnroll.course.dto.PaymentDTO;
import com.courseEnroll.course.model.Payment;
import com.courseEnroll.course.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public Payment createPayment(@RequestBody @Valid PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setPaymentId(paymentDTO.getPaymentId());
        payment.setPayerName(paymentDTO.getPayerName());
        payment.setAmount(paymentDTO.getAmount());
        payment.setMethod(paymentDTO.getMethod());
        payment.setStatus(paymentDTO.getStatus());

        // Setting the payment date from DTO
        payment.setPaymentDate(paymentDTO.getPaymentDate());

        return paymentService.createPayment(payment);
    }

    @GetMapping
    public List<PaymentDTO> getAllPayments() {
        return paymentService.getAllPayments().stream().map(payment -> {
            PaymentDTO dto = new PaymentDTO();
            dto.setPaymentId(payment.getPaymentId());
            dto.setPayerName(payment.getPayerName());
            dto.setAmount(payment.getAmount());
            dto.setMethod(payment.getMethod());
            dto.setStatus(payment.getStatus());
            return dto;
        }).collect(Collectors.toList());
    }

    @PostMapping("/verify")
    public boolean verifyPayment(@RequestParam String paymentId, @RequestParam String signature) {
        return paymentService.verifyPayment(paymentId, signature);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }
    @GetMapping("/admin-view/{id}")
    public ResponseEntity<PaymentAdminDTO> getAdminPayment(@PathVariable Long id) {
        PaymentAdminDTO dto = paymentService.getAdminPayment(id);
        return ResponseEntity.ok(dto);
    }

    }