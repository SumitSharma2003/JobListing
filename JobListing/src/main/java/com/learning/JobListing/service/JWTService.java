package com.learning.JobListing.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {
    private String secretKey;
    public JWTService(){
        try {
            KeyGenerator generator= KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk= generator.generateKey();
            secretKey= Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String extractUsername(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validate(String token, UserDetails userDetails) {

        String username = extractUsername(token);

        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token) {

        Date expiration = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return expiration.before(new Date());
    }
    public String generateToken(String username){

        Map<String,Object> claims=new HashMap<>();
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+30*60*60*1000))
                .signWith(getKey())
                .compact();

    }
    private Key getKey(){
        byte b[]= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(b);

    }

}
