package com.courseEnroll.course.service;

import com.courseEnroll.course.dto.AdminDto;
import com.courseEnroll.course.exception.AdminNotFoundException;
import com.courseEnroll.course.model.Admin;
import com.courseEnroll.course.model.User;
import com.courseEnroll.course.repository.AdminRepository;
import com.courseEnroll.course.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    // 🔹 Create Admin from DTO
    public Admin createAdmin(AdminDto adminDto) {
        User user = userRepository.findById(adminDto.getUserId())
                .orElseThrow(() -> new AdminNotFoundException("User not found with ID: " + adminDto.getUserId()));

        Admin admin = new Admin();
        admin.setName(adminDto.getName());
        admin.setEmail(adminDto.getEmail());
        admin.setPhone(adminDto.getPhone());
        admin.setUser(user);

        return adminRepository.save(admin);
    }

    // 🔹 Update Admin from DTO
    public Admin updateAdmin(Long id, AdminDto adminDto) {
        Admin existingAdmin = getAdminById(id);

        existingAdmin.setName(adminDto.getName());
        existingAdmin.setEmail(adminDto.getEmail());
        existingAdmin.setPhone(adminDto.getPhone());

        User user = userRepository.findById(adminDto.getUserId())
                .orElseThrow(() -> new AdminNotFoundException("User not found with ID: " + adminDto.getUserId()));
        existingAdmin.setUser(user);

        return adminRepository.save(existingAdmin);
    }

    // 🔹 Get all Admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // 🔹 Get Admin by ID
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found with ID: " + id));
    }

    // 🔹 Get Admin by Email
    public Admin getAdminByEmail(String email) {
        return adminRepository.findByEmail(email)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found with email: " + email));
    }

    // 🔹 Get Admin by Phone
    public Admin getAdminByPhone(String phone) {
        return adminRepository.findByPhone(phone)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found with phone: " + phone));
    }

    // 🔹 Delete Admin
    public void deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new AdminNotFoundException("Cannot delete — Admin not found with ID: " + id);
        }
        adminRepository.deleteById(id);
    }

    // 🔹 Check existence
    public boolean adminExistsByEmail(String email) {
        return adminRepository.existsByEmail(email);
    }

    public boolean adminExistsByPhone(String phone) {
        return adminRepository.existsByPhone(phone);
    }
}