package com.example.securitydemo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User Controller", description = "User controller for Security Demo Application")
@RestController
@RequestMapping("/user")
public class UserController {

    @Operation(summary = "User welcome message")
    @GetMapping("/welcome")
    public String welcome() {
        return "Hi User! Welcome to the Security Demo App!";
    }
}
