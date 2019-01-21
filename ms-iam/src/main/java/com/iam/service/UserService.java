package com.iam.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ecommerce.exception.AuthenticationException;
import com.ecommerce.exception.AuthorizationException;
import com.ecommerce.exception.ExceptionMessages;
import com.iam.api.dto.AuthResponseDTO;
import com.iam.api.dto.UserDTO;
import com.iam.config.SecurityConfig;
import com.iam.domain.User;
import com.iam.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final SecurityConfig securityConfig;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(SecurityConfig securityConfig, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.securityConfig = securityConfig;
        this.userRepository =  userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void register(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setSecret(UUID.randomUUID().toString());
        userRepository.save(user);
    }

    public AuthResponseDTO authenticate(UserDTO userDTO) throws AuthenticationException {
        User user = userRepository.findByUsername(userDTO.username);

        if (bCryptPasswordEncoder.matches(userDTO.password, user.getPassword())) {

            Algorithm algorithm = Algorithm.HMAC256(user.getSecret());

            try {
                String token = JWT.create()
                        .withClaim("user", user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + securityConfig.getExpirationTime()))
                        .sign(algorithm);
                return new AuthResponseDTO("Bearer ".concat(token));
            }catch (Exception ex){
                logger.error("Error on create token", ex);
                throw new AuthenticationException(ExceptionMessages.AUTHENTICATION_ERROR);
            }
        }

        throw new AuthenticationException(ExceptionMessages.INVALID_CREDENTIALS);
    }

    public void authorize(AuthResponseDTO authResponseDTO) throws AuthorizationException {
        try{
            String authToken = authResponseDTO.authToken.split(" ")[1];

            DecodedJWT decodedJWT = JWT.decode(authToken);

            User user = userRepository.findByUsername(decodedJWT.getClaim("user").asString());

            Algorithm algorithm = Algorithm.HMAC256(user.getSecret());

            JWTVerifier verifier = JWT
                    .require(algorithm)
                    .build();

            verifier.verify(authToken);
        }catch (Exception ex){
            logger.error("Error on verify token", ex);
            throw new AuthorizationException(ExceptionMessages.AUTHORIZATION_ERROR);
        }
    }
}