package com.courseEnroll.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationResponse {
    private String status;
    private String message;
    private String username;
    private String email;
    private String role;
}