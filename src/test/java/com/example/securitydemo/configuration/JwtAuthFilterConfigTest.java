package com.example.securitydemo.configuration;

import com.example.securitydemo.service.JwtService;
import com.example.securitydemo.service.UserDetailServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JwtAuthFilterConfigTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDetailServiceImpl userDetailServiceImpl;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtAuthFilterConfig jwtAuthFilterConfig;

    @Test
    void doFilterInternal_noAuthHeader() throws ServletException, IOException {
        // GIVEN
        when(request.getHeader("Authorization")).thenReturn(null);

        // WHEN
        jwtAuthFilterConfig.doFilterInternal(request, response, filterChain);

        // THEN
        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(jwtService, userDetailServiceImpl); //Verifica che non siano stati chiamati altri metodi
    }

    @Test
    void doFilterInternal_invalidAuthHeader() throws ServletException, IOException {
        // GIVEN
        when(request.getHeader("Authorization")).thenReturn("Bearer"); // Manca il token

        // WHEN
        jwtAuthFilterConfig.doFilterInternal(request, response, filterChain);

        // THEN
        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(jwtService, userDetailServiceImpl);
    }

    @Test
    void doFilterInternal_validToken_userExists() throws ServletException, IOException {
        // GIVEN
        String jwt = "test_jwt";
        String username = "test_user";
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                        .username(username)
                        .password("password")
                        .roles("USER")
                        .build();


        when(request.getHeader("Authorization")).thenReturn("Bearer " + jwt);
        when(jwtService.extractUsername(jwt)).thenReturn(username);
        when(userDetailServiceImpl.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtService.isTokenValid(jwt)).thenReturn(true);

        // WHEN
        jwtAuthFilterConfig.doFilterInternal(request, response, filterChain);

        // THEN
        verify(filterChain).doFilter(request, response);
        verify(jwtService).extractUsername(jwt);
        verify(userDetailServiceImpl).loadUserByUsername(username);
        verify(jwtService).isTokenValid(jwt);
        //Verifica che l'autenticazione sia stata settata nel SecurityContextHolder
        UsernamePasswordAuthenticationToken expectedAuthentication = new UsernamePasswordAuthenticationToken(
                username,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
        expectedAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        org.junit.jupiter.api.Assertions.assertEquals(expectedAuthentication, SecurityContextHolder.getContext().getAuthentication());

    }


    @Test
    void doFilterInternal_validToken_userNotFound() throws ServletException, IOException {
        // GIVEN
        String jwt = "test_jwt";
        String username = "test_user";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + jwt);
        when(jwtService.extractUsername(jwt)).thenReturn(username);
        when(userDetailServiceImpl.loadUserByUsername(username)).thenReturn(null); //Simula che l'utente non esista

        // WHEN
        jwtAuthFilterConfig.doFilterInternal(request, response, filterChain);

        // THEN
        verify(filterChain).doFilter(request, response);
        verify(jwtService).extractUsername(jwt);
        verify(userDetailServiceImpl).loadUserByUsername(username);
        //Verifica che l'autenticazione NON sia stata settata
        org.junit.jupiter.api.Assertions.assertNull(SecurityContextHolder.getContext().getAuthentication());
    }


    @Test
    void doFilterInternal_validToken_invalidToken() throws ServletException, IOException {
        // GIVEN
        String jwt = "test_jwt";
        String username = "test_user";
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                .username(username)
                .password("password")
                .roles("USER")
                .build();

        when(request.getHeader("Authorization")).thenReturn("Bearer " + jwt);
        when(jwtService.extractUsername(jwt)).thenReturn(username);
        when(userDetailServiceImpl.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtService.isTokenValid(jwt)).thenReturn(false); // Token invalido

        // WHEN
        jwtAuthFilterConfig.doFilterInternal(request, response, filterChain);

        // THEN
        verify(filterChain).doFilter(request, response);
        verify(jwtService).extractUsername(jwt);
        verify(userDetailServiceImpl).loadUserByUsername(username);
        verify(jwtService).isTokenValid(jwt);
        org.junit.jupiter.api.Assertions.assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
}