package com.revature.P1.services;

import com.revature.P1.dtos.responses.PrincipalResponse;
import com.revature.P1.utils.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import java.util.Date;

public class TokenService {
    private JwtConfig jwtConfig;

    public TokenService() {
        super();
    }

    public TokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(PrincipalResponse subject) {
        long now = System.currentTimeMillis();
        JwtBuilder tokenBuilder = Jwts.builder()
                .setId(subject.getuID())
                .setIssuer("P1")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration()))
                .setSubject(subject.getuName())
                .claim("role", subject.getRole())
                .claim("email", subject.getEmail())
                .claim("first", subject.getFirst())
                .claim("last", subject.getLast())
                .claim("active", subject.isActive())
                .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());

        return tokenBuilder.compact();
    }

    public PrincipalResponse extractRequesterDetails(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();

            return new PrincipalResponse(claims.getId(), claims.getSubject(), claims.get("email", String.class), claims.get("first", String.class), claims.get("last", String.class), Boolean.parseBoolean(claims.get("active", String.class)), claims.get("role", String.class));
        } catch (Exception e) {
            return null;
        }
    }
}