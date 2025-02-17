package com.example.securitydemo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AuthorizationControllerTest {

    private final AuthorizationController authorizationController = new AuthorizationController();
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(authorizationController).build();

    @Test
    void welcome() throws Exception {
        mockMvc.perform(get("/authorization/welcome"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome to the Security Demo App! Please register a new User or use the authorization API to start..."));
    }
}