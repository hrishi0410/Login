package com.courseEnroll.course.service.impl;

import com.courseEnroll.course.model.Instructor;
import com.courseEnroll.course.repository.InstructorRepository;
import com.courseEnroll.course.service.InstructorService;
import com.courseEnroll.course.exception.InstructorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Override
    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new InstructorNotFoundException("Instructor not found with ID: " + id));
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor updateInstructor(Long id, Instructor updatedInstructor) {
        Instructor existing = getInstructorById(id);
        existing.setName(updatedInstructor.getName());
        existing.setBio(updatedInstructor.getBio());
        // Add more fields if needed
        return instructorRepository.save(existing);
    }

    @Override
    public void deleteInstructor(Long id) {
        if (!instructorRepository.existsById(id)) {
            throw new InstructorNotFoundException("Instructor not found with ID: " + id);
        }
        instructorRepository.deleteById(id);
    }
}