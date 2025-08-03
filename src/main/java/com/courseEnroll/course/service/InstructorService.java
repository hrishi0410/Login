package com.courseEnroll.course.service;

import com.courseEnroll.course.model.Instructor;
import java.util.List;

    public interface InstructorService {

        Instructor createInstructor(Instructor instructor);

        Instructor getInstructorById(Long id);

        Instructor updateInstructor(Long id, Instructor updatedInstructor);

        void deleteInstructor(Long id);

        List<Instructor> getAllInstructors();
    }
