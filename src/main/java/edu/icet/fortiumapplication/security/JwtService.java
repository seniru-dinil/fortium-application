package edu.icet.fortiumapplication.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {


    @Value("${spring.security.jwt.token}")
    private String SECRET_KEY;


    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claims()
                .add("role",userDetails.getAuthorities())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+(1000*60)))
                .and()
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }



    public Claims getClaims(String token){
        try{
            return Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }catch (ExpiredJwtException ex){
            throw new JwtException("EXPIRED TOKEN");
        }catch (SignatureException ex){
            throw new JwtException("JWT signature does not match locally computed signature.");
        }catch (Exception exception){
            throw new JwtException("internal server error");
        }
    }

    public String getEmail(String token){
        return getClaims(token).getSubject();
    }


}
