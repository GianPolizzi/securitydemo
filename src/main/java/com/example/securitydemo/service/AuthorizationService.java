package com.example.securitydemo.service;

import com.example.securitydemo.model.dto.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servizio che gestisce l'autenticazione dell'utente.
 * Se l'utente è registrato nel DB, viene rilasciato un token JWT per autorizzare le future richieste.
 */
@Service
public class AuthorizationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    public String getAuthenticationToken(LoginForm loginForm){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.username(), loginForm.password())
        );
        if(authentication.isAuthenticated()){
            return "Token: " + jwtService.generateToken(userDetailServiceImpl.loadUserByUsername(loginForm.username()));
        } else {
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }
}
