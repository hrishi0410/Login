package com.courseEnroll.course.dto;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String username;
    private String password;
    private String role; // e.g., "ADMIN" or "USER"
    private String email;
}