package com.courseEnroll.course.service;

import com.courseEnroll.course.model.User;
import java.util.List;

public interface UserService {

    User createUser(User user);            // save a new user
    User getUserByEmail(String email);     // fetch user by email
    List<User> getAllUsers();              // retrieve all users
    void deleteUserById(Long id);          // delete user by id
}