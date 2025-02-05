package com.example.securitydemo;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

/**
 * Classe di test creata per la generazione corretta della Secret key da integrare nella costante SECRET
 */
public class JwtSecretMakerTest {

    @Test
    public void generateSecretKey(){
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String encodedKey = DatatypeConverter.printHexBinary(key.getEncoded());
        System.out.println("Key = [" + encodedKey + "]");
    }
}