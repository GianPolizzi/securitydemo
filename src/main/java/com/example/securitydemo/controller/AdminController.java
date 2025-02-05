package com.example.securitydemo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Admin Controller", description = "Admin controller for Security Demo Application")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Operation(summary = "Admin welcome message")
    @GetMapping("/welcome")
    public String welcome() {
        return "Hi Admin! Welcome to the Security Demo App!";
    }
}
