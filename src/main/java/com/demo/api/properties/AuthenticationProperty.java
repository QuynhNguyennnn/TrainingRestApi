package com.demo.api.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Authentication Property.
 * 
 * @author QuynhNN
 */
@Data
@ConfigurationProperties(prefix = "app.token")
@Component
public class AuthenticationProperty {
    private int expired;
    private int refreshExpired;
    private String secret;
    private String refresh_secret;
}
