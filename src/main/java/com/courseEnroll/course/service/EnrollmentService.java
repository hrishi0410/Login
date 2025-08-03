package com.courseEnroll.course.service;

import com.courseEnroll.course.model.Enrollment;
import java.util.List;

public interface EnrollmentService {

    Enrollment enrollStudent(Long studentId, Long courseId, Long paymentId);

    List<Enrollment> getEnrollmentsByStudent(Long studentId);

    List<Enrollment> getEnrollmentsByCourse(Long courseId);

    List<Enrollment> getAllEnrollments();

    void deleteEnrollment(Long id);
}