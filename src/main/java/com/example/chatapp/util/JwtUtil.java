package com.example.chatapp.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;  // HttpServletRequest import 추가

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "mySecretKey";

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1시간 유효
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractToken(HttpServletRequest request) {
        // JWT 토큰을 HTTP 요청의 Authorization 헤더에서 추출
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);  // "Bearer " 부분을 제거하고 토큰만 반환
        }
        return null;
    }
}
