package com.courseEnroll.course.repository;

import com.courseEnroll.course.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

    public interface PaymentRepository extends JpaRepository<Payment, Long> {

        Optional<Payment> findByPaymentGatewayId(String paymentGatewayId);

        List<Payment> findByStudent_Id(Long studentId);
    }
