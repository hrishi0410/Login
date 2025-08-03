package com.courseEnroll.course.service.impl;

import com.courseEnroll.course.model.Course;
import com.courseEnroll.course.model.Enrollment;
import com.courseEnroll.course.model.Payment;
import com.courseEnroll.course.model.Student;
import com.courseEnroll.course.repository.CourseRepository;
import com.courseEnroll.course.repository.EnrollmentRepository;
import com.courseEnroll.course.repository.StudentRepository;
import com.courseEnroll.course.service.EnrollmentService;
import com.courseEnroll.course.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PaymentService paymentService;

    @Override
    public Enrollment enrollStudent(Long studentId, Long courseId, Long paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        if (payment == null || !payment.isVerified()) {
            throw new RuntimeException("Payment not verified or not found.");
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDateTime.now());

        payment.setEnrollment(enrollment);

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        paymentService.createPayment(payment);

        return savedEnrollment;
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudent(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourse(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }
}