package com.example.securitydemo.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {

    private final static String SECRET = "70CAB8EAE9FD6E8C36F27470B487054E3596F581A0FBFAAA0A756844294F8AA42199C6C9901145CF4A90D0577BCCF7B0FE0361E86CD1EF9F64C2AA16A0CD84FA";

    private final static long VALIDITY_IN_MINUTES = 30;

    public String generateToken(UserDetails userDetails){
        Map<String, String> claims = new HashMap<>();
        claims.put("iss", "https://mioclient.com");
        //claims.put("name", "Gianmarco");
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusMillis(TimeUnit.MINUTES.toMillis(VALIDITY_IN_MINUTES))))
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey(){
        byte[] decodedKey = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(decodedKey);
    }
}
