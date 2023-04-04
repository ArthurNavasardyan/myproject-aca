package com.aca.myprojectaca.config.jwt;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateJwtToken(Authentication authentication){

        //UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((authentication.getName()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 2*60000))
                .signWith(SignatureAlgorithm.HS512,getSigninKey())
                .compact();
    }
    private Key getSigninKey()
    {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public boolean validateJwtToken(String jwt) {
        try {
            Jwts.parser().setSigningKey(getSigninKey()).parseClaimsJws(jwt);
            return true;
        }catch (MalformedJwtException e){
            System.err.println(e.getMessage());
        }catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
        return false;
    }

    public String getUserNameFromJwtToken(String jwt){
        return Jwts.parser().setSigningKey(getSigninKey()).parseClaimsJws(jwt).getBody().getSubject();
    }
}
