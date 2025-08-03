package com.courseEnroll.course.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Ensures title is required in DB
    private String title;

    private String description;

    @Column(nullable = false)
    private Double price;

    // Many-to-One relationship with Instructor
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    // One-to-Many relationship with Enrollment
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments = new ArrayList<>();
}