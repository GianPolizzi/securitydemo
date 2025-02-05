package com.example.securitydemo.service;

import com.example.securitydemo.model.entity.User;
import com.example.securitydemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public String register(User user){
        if(!isUserAlreadyExist(user.getUsername())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            log.info("User: {} correctly saved!", user.getUsername());
            return "User " + user.getUsername() + " correctly saved!";
        }
        log.warn("Sorry! User with username: {} already exist!", user.getUsername());
        return "Sorry! User with username: " + user.getUsername() + " already exist!";
    }

    public boolean isUserAlreadyExist(String username){
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent();
    }
}
