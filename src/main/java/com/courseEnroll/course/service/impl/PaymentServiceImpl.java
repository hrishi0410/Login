package com.courseEnroll.course.service.impl;

import com.courseEnroll.course.dto.PaymentAdminDTO;
import com.courseEnroll.course.dto.PaymentPublicDTO;
import com.courseEnroll.course.mapper.PaymentMapper;
import com.courseEnroll.course.model.Payment;
import com.courseEnroll.course.repository.PaymentRepository;
import com.courseEnroll.course.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public boolean verifyPayment(String paymentId, String signature) {
        // Placeholder: implement real Razorpay signature verification
        return paymentId != null && signature != null && signature.startsWith("rzp_");
    }
    public PaymentPublicDTO getStudentPayment(Long id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        return paymentMapper.toPublicDTO(payment);
    }
    @Override
    public PaymentAdminDTO getAdminPayment(Long id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        return paymentMapper.toAdminDTO(payment);
    }

}