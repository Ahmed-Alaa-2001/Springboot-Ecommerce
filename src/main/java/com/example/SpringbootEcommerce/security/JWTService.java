package com.example.SpringbootEcommerce.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.SpringbootEcommerce.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.secret.token}")
    private String secretToken;
    @Value("${jwt.expiry.time}")
    private int expiryTime;
    @Value("${jwt.issuer}")
    private String issuer;
    private Algorithm algorithm;
    @PostConstruct
    public void postConstruct(){
        algorithm=Algorithm.HMAC256(secretToken);
    }
    public String generateJWT(User user){
        return JWT.create()
                .withClaim("USERNAME",user.getUserName())
                .withExpiresAt(new Date(System.currentTimeMillis()+(expiryTime*1000)))
                .withIssuer(issuer)
                .sign(algorithm);
    }
}
