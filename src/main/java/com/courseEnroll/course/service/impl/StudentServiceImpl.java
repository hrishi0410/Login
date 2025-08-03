package com.courseEnroll.course.service.impl;

import com.courseEnroll.course.model.Student;
import com.courseEnroll.course.repository.StudentRepository;
import com.courseEnroll.course.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        studentRepository.findByEmail(student.getEmail()).ifPresent(s -> {
            throw new IllegalArgumentException("Email already registered");
        });
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public Student updateStudent(Long id, Student updatedStudent) {
        Student existing = getStudentById(id);

        if (!existing.getEmail().equals(updatedStudent.getEmail())) {
            studentRepository.findByEmail(updatedStudent.getEmail()).ifPresent(s -> {
                throw new IllegalArgumentException("Email already registered");
            });
        }

        existing.setName(updatedStudent.getName());
        existing.setEmail(updatedStudent.getEmail());
        return studentRepository.save(existing);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}