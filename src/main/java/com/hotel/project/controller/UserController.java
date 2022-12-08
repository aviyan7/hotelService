package com.hotel.project.controller;

import com.hotel.project.service.UserService;
import com.hotel.project.model.User;
import com.hotel.project.model.UserRole;
import com.hotel.project.model.Role;
import com.hotel.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public User createUser(@RequestBody User user) throws Exception {
        // Encrypting user password
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        user.setProfile("default.png");
        Set<UserRole> roles = new HashSet<>();

        Role role = new Role();

        // for default admin
        if (user.getUsername().equalsIgnoreCase("hoho")) {
            role.setRoleId(10L);
            role.setRoleName("ADMIN");
        } else {
            role.setRoleId(11L);
            role.setRoleName("NORMAL");
        }

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);

        return userService.createUser(user, roles);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public User getUser(@PathVariable("id") Long id) {
        return this.userService.getUserById(id);
    }

    @GetMapping("/{username}")
    @ResponseStatus(code = HttpStatus.OK)
    public User getUser(@PathVariable("username") String username) {
        return this.userService.getUserByUsername(username);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user.getId(), user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteUsersById(@PathVariable("id") Long id) throws Exception {
        userService.deleteUserById(id);
    }
}
