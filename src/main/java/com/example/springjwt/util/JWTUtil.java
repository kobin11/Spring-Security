package com.example.springjwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JWTUtil {
    private static final String SECRET_KEY ="123456789";
    private static final int TOKEN_VALIDITY = 3600 * 5;

    private String getUserNameFromToken(String token)
    {

        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token)
    {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }


    public Boolean validateToken(String token, UserDetails userDetails)
    {
        final String username = getUserNameFromToken(token);

        return (username.equals(userDetails) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {

        final Date expire = getClaimFromToken(token, Claims::getExpiration);
        return expire.before(new Date());
    }

    public String generateToken(UserDetails userDetails)
    {
        Map<String,Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

}
