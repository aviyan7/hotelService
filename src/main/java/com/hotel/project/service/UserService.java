package com.hotel.project.service;

import com.hotel.project.model.*;
import java.util.List;
import java.util.Set;

public interface UserService {

    User createUser(User user, Set<UserRole> userRoles) throws Exception;

    List<User> getUsers();

    User getUserByUsername(String username);

    void deleteUserById(Long id) throws Exception;

    User getUserById(Long id);

    User updateUser(Long id, User user);
}
