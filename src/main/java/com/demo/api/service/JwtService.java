package com.demo.api.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static com.demo.api.constants.ErrorMessage.USERNAME_NOT_FOUND;
import static com.demo.api.constants.ErrorMessage.INVALID_CREDENTIALS;
import static com.demo.api.constants.ErrorCode.INVALID_CREDENTIAL;
import com.demo.api.entities.UserInfo;
import com.demo.api.errors.ApiError;
import com.demo.api.exceptions.BadRequestException;
import com.demo.api.model.request.AuthRequest;
import com.demo.api.model.request.RefreshRequest;
import com.demo.api.model.response.JwtResponse;
import com.demo.api.properties.AuthenticationProperty;
import com.demo.api.repositories.StaffRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Jwt Service.
 * 
 * @author QuynhNN
 */
@Component
public class JwtService {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private AuthenticationProperty authenticationProperty;

    /**
     * Check username is valid or not.
     * 
     * @param authRequest input username and password.
     * @return jwtResponse (token and refresh token)
     */
    public JwtResponse checkValidUsername(AuthRequest authRequest) {
        UserInfo userInfo = staffRepository.getByUserName(authRequest.getUsername());
        if (userInfo != null) {
            if (Objects.equals(userInfo.getPassword(), authRequest.getPassword())) {
                JwtResponse jwtResponse = new JwtResponse();
                jwtResponse.setAccessToken(generateToken(userInfo.getUsername()));
                jwtResponse.setRefreshToken(generateRefreshToken(userInfo.getUsername()));
                return jwtResponse;
            }
        } else {
            throw new UsernameNotFoundException(messageSource.getMessage(USERNAME_NOT_FOUND, null, Locale.ENGLISH));
        }
        return null;
    }

    /**
     * Check an input refresh token is valid or not.
     * 
     * @param refreshRequest the refreshToken 
     * @return new jwtResponse or exception
     */
    public JwtResponse checkValidRefreshToken(RefreshRequest refreshRequest) {
        if (validateToken(refreshRequest)) {
            JwtResponse jwtResponse = new JwtResponse();
            String username = extractUserName(refreshRequest.getRefreshToken());
            jwtResponse.setAccessToken(generateToken(username));
            jwtResponse.setRefreshToken(generateRefreshToken(username));
            return jwtResponse;
        } else {
            throw new BadRequestException(new ApiError(INVALID_CREDENTIAL,
                    messageSource.getMessage(INVALID_CREDENTIALS, null, Locale.ENGLISH)));
        }

    }

    /**
     * Extract username from token.
     * 
     * @param token the token need to check.
     * @return username.
     */
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extract expiration date of token.
     * 
     * @param token the token need to check.
     * @return date of token.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Exctract all claim in token.
     * 
     * @param <T> 
     * @param token the token.
     * @param claimsResolver a function to reslove the claim.
     * @return all claim applied.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Exctract all claim in token.
     * 
     * @param token the token.
     * @return jwts values.
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Function to check expiration of token.
     * 
     * @param token token.
     * @return true if unexpired.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Validate the refresh token.
     * 
     * @param refreshRequest input refresh token.
     * @return true or false.
     */
    public Boolean validateToken(RefreshRequest refreshRequest) {
        final String username = extractUserName(refreshRequest.getRefreshToken());
        return (staffRepository.isUsernameExist(username)) && !isTokenExpired(refreshRequest.getRefreshToken());
    }

    /**
     * Generate token.
     * 
     * @param userName input username.
     * @return token generated.
     */
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    /**
     * Generate refresh token.
     * @param userName input username.
     * @return refresh token generated.
     */
    public String generateRefreshToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createRefreshToken(claims, userName);
    }

    /**
     * Create token function.
     * 
     * @param claims notification.
     * @param userName username.
     * @return jwts token generated.
     */
    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + authenticationProperty.getExpired()))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    /**
     * Create refresh token function.
     * 
     * @param claims notification.
     * @param userName username.
     * @return jwts refresh token generated.
     */
    private String createRefreshToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + authenticationProperty.getRefreshExpired()))
                .signWith(getSignRefreshKey(), SignatureAlgorithm.HS256).compact();
    }

    /**
     * Generate sign key for token.
     * 
     * @return sign key
     */
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(authenticationProperty.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Generate sign key for refresh token.
     * 
     * @return sign key.
     */
    private Key getSignRefreshKey() {
        byte[] keyBytes = Decoders.BASE64.decode(authenticationProperty.getRefresh_secret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
