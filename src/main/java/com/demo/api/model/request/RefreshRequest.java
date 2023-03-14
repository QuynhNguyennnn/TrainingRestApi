package com.demo.api.model.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Refresh Request.
 * 
 * @author QuynhNN
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshRequest {
    @NotNull (message = "Refresh token can not be blank")
    private String refreshToken;
}
