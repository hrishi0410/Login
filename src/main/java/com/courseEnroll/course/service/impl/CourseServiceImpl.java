package com.courseEnroll.course.service.impl;

import com.courseEnroll.course.dto.CourseDTO;
import com.courseEnroll.course.model.Course;
import com.courseEnroll.course.repository.CourseRepository;
import com.courseEnroll.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.courseEnroll.course.exception.ResourceNotFoundException;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public CourseDTO addCourse(CourseDTO courseDTO) {
        Course course = mapToEntity(courseDTO);
        Course savedCourse = courseRepository.save(course);
        return mapToDTO(savedCourse);
    }

    @Override
    public CourseDTO updateCourse(Long courseId, CourseDTO courseDTO) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        // update other fields...
        return mapToDTO(courseRepository.save(course));
    }

    @Override
    public boolean deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
        return true;
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        return mapToDTO(course);
    }

    // 🔄 Mapping Helpers
    private Course mapToEntity(CourseDTO dto) {
        Course course = new Course();
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        // map other fields...
        return course;
    }

    private CourseDTO mapToDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        // map other fields...
        return dto;
    }
    @Override
    public Page<CourseDTO> getCoursesPaged(Pageable pageable) {
        return courseRepository.findAll(pageable)
                .map(this::mapToDTO);
    }

}