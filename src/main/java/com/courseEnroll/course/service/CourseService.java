package com.courseEnroll.course.service;

import com.courseEnroll.course.dto.CourseDTO;
import com.courseEnroll.course.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {

    CourseDTO addCourse(CourseDTO courseDTO);

    CourseDTO getCourseById(Long id);

    List<CourseDTO> getAllCourses();

    boolean deleteCourse(Long id);

    CourseDTO updateCourse(Long id, CourseDTO courseDTO);

    Page<CourseDTO> getCoursesPaged(Pageable pageable);

}