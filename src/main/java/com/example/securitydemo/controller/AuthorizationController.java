package com.example.securitydemo.controller;

import com.example.securitydemo.model.dto.LoginForm;
import com.example.securitydemo.model.entity.User;
import com.example.securitydemo.service.AuthorizationService;
import com.example.securitydemo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authorization Controller", description = "Authorization controller for Security Demo Application")
@RestController
@RequestMapping("/authorization")
public class AuthorizationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationService authorizationService;

    @Operation(summary = "Welcome message")
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Security Demo App! Please register a new User or use the authorization API to start...";
    }

    @Operation(summary = "Register new user")
    @PostMapping("/register")
    public String register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/authenticate")
    public String getAuthenticationToken(@RequestBody LoginForm loginForm){
        return authorizationService.getAuthenticationToken(loginForm);
    }
}
