package com.example.contenttest.services;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.contenttest.exception.BaseForbiddenException;
import com.example.contenttest.model.Users;
import com.example.contenttest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class TokenService {


    private final UserRepository userRepository;

    @Value("TestingToken")
    private String secret;

    @Value("ContentTest")
    private String issuer;

    public TokenService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String tokenize(Users user) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 60);
        Date expiresAt = calendar.getTime();
        return JWT.create()
                .withIssuer(issuer)
                .withClaim("principal", user.getId())
                .withClaim("role", "USER")
                .withExpiresAt(expiresAt)
                .sign(algorithm());
    }

    public DecodedJWT verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm())
                    .withIssuer(issuer)
                    .build();

            return verifier.verify(token);

        } catch (Exception e) {
            return null;
        }
    }

    private Algorithm algorithm() {
        return Algorithm.HMAC256(secret);
    }

    public Users getUserByToken() throws BaseForbiddenException {
        String userId = this.userId();

        Optional<Users> opt = userRepository.findById(userId);
        if (opt.isEmpty()) throw BaseForbiddenException.Forbidden();

        Users user = opt.get();
        return user;
    }

    public String userId() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return (String) authentication.getPrincipal();
    }
}

