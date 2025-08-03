package com.courseEnroll.course.service;
import com.courseEnroll.course.model.Student;
import java.util.List;

    public interface StudentService {

        Student createStudent(Student student);

        Student getStudentById(Long id);

        Student updateStudent(Long id, Student updatedStudent);

        void deleteStudent(Long id);

        List<Student> getAllStudents();
    }
