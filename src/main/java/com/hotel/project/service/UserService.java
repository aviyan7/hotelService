package com.hotel.project.service;

import java.util.List;
import java.util.Set;

import com.hotel.project.model.User;
import com.hotel.project.model.UserRole;

public interface UserService {

    User createUser(User user, Set<UserRole> userRoles) throws Exception;

    List<User> getUsers();

    User getUserByUsername(String username);

    void deleteUserById(Long id) throws Exception;

    User getUserById(Long id);

    User updateUser(Long id, User user);
    
//    User updateUserPassword(User user, String password);
    User updateUserPassword(Long id, String password, String oldpassword);
}
