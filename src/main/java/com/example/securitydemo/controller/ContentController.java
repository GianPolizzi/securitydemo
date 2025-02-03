package com.example.securitydemo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Content Controller", description = "Content controller for Security Demo Application")
@RestController
public class ContentController {

    @Operation(summary = "Hello message")
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
