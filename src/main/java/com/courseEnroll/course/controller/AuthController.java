package com.courseEnroll.course.controller;

import com.courseEnroll.course.dto.*;
import com.courseEnroll.course.model.User;
import com.courseEnroll.course.repository.UserRepository;
import com.courseEnroll.course.service.JwtService;
import com.courseEnroll.course.service.CustomUserDetailsService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          CustomUserDetailsService userDetailsService,
                          UserRepository userRepo,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()
                    )
            );
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return ResponseEntity.status(401).body(Map.of("error", "Bad credentials"));
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String jwtToken = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(jwtToken));
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody UserRegistrationRequest request) {
        if (userDetailsService.userExists(request.getUsername())) {
            return ResponseEntity.badRequest().body(
                    new RegistrationResponse("error", "Username already taken", null, null, null)
            );
        }

        String role = request.getRole();
        if (role == null || role.isBlank()) {
            return ResponseEntity.badRequest().body(
                    new RegistrationResponse("error", "Role is required", null, null, null)
            );
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(role.toUpperCase());
        newUser.setEmail(request.getEmail());

        userRepo.save(newUser);

        RegistrationResponse response = new RegistrationResponse(
                "success",
                "User registered successfully",
                newUser.getUsername(),
                newUser.getEmail(),
                newUser.getRole()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public String test() {
        return "AuthController is working!";
    }

    //  Manual Token Generator for Testing
    @GetMapping("/generate-token")
    public ResponseEntity<String> generateTokenManually() {
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "hrishi",
                "password",
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );

        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(token);
    }
}