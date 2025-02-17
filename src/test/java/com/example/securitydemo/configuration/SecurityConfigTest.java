package com.example.securitydemo.configuration;

import com.example.securitydemo.service.UserDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SecurityConfigTest {

    @Mock
    private UserDetailServiceImpl userDetailServiceImpl;

    @InjectMocks
    private SecurityConfig securityConfig;


    @Test
    void userDetailsService() {
        UserDetailsService userDetailsService = securityConfig.userDetailsService();
        org.junit.jupiter.api.Assertions.assertEquals(userDetailServiceImpl, userDetailsService);
    }

    @Test
    void getAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = (DaoAuthenticationProvider) securityConfig.getAuthenticationProvider();
        org.junit.jupiter.api.Assertions.assertNotNull(authProvider);
    }

    @Test
    void getAuthenticationManager() {
        AuthenticationManager authManager = securityConfig.getAuthenticationManager();
        assertInstanceOf(ProviderManager.class, authManager);
    }

    @Test
    void getPasswordEncoder() {
        PasswordEncoder passwordEncoder = securityConfig.getPasswordEncoder();
        assertInstanceOf(BCryptPasswordEncoder.class, passwordEncoder);
    }
}