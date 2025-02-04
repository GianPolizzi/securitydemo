package com.example.securitydemo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authorization Controller", description = "Authorization controller for Security Demo Application")
@RestController
@RequestMapping("/authorization")
public class AuthorizationController {

    @Operation(summary = "Welcome message")
    @GetMapping("/welcome")
    public String hello() {
        return "Welcome to the Security Demo App! Please use Authorization API to start...";
    }
}
