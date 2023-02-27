package com.demo.api.model.request;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank (message = "Refresh token can not be blank")
    private String refreshToken;
}
