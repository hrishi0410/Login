package com.courseEnroll.course.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseDTO {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private double price;
}