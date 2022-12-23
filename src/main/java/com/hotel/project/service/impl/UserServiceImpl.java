package com.hotel.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotel.project.model.User;
import com.hotel.project.model.UserRole;
import com.hotel.project.repository.RoleRepository;
import com.hotel.project.repository.UserRepository;
import com.hotel.project.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User localUser = userRepository.findByUsername(user.getUsername());

        if (localUser != null) {
            throw new Exception("User already exits!!!");
        } else {
            for (UserRole userRole : userRoles) {
                roleRepository.save(userRole.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            localUser = userRepository.save(user);
        }
        return localUser;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser != null) return optionalUser.get();
        return null;
    }

    @Override
    public User updateUser(Long id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User updatedUser = optionalUser.get();
            updatedUser.setUsername(user.getUsername());
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setGender(user.getGender());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPhone(user.getPhone());

            return userRepository.save(updatedUser);
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUserById(Long id) throws Exception {
        if (userRepository.findById(id).isPresent()) userRepository.deleteById(id);
        else throw new Exception("User with id " + id + " doesn't exits !!!");
    }
    
    @Override
    public User updateUserPassword(Long id, String password, String oldpassword) {
    	Optional<User> optionalUser = userRepository.findById(id);
    	System.out.println(oldpassword);
    	System.out.println(password);
        if (optionalUser != null) {
        	User updatedUser = optionalUser.get();
        	System.out.println(updatedUser.getPassword());
        	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        	String encodedPassword = passwordEncoder.encode(oldpassword);
        	System.out.println(encodedPassword);
        	if(updatedUser.getPassword()==encodedPassword) {
        		System.out.println("password old "+oldpassword);
        		System.out.println("password new "+password);
        	}
        	BCryptPasswordEncoder passwordEncoder1 = new BCryptPasswordEncoder();
        	String encodedPassword1 = passwordEncoder.encode(password);
        	System.out.println("password new "+encodedPassword1);
//        	updatedUser.setPassword(encodedPassword);
//        	userRepository.save(updatedUser);
        }
        return null;
    }
//    public User updateUserPassword(Long id) {
//    	Optional<User> optionalUser = userRepository.findById(id);
//        if (optionalUser != null) return 
//        return null;
//    }
  
}
