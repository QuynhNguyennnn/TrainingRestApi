package com.demo.api.model.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Staff Register Request.
 * 
 * @author QuynhNN 
 */
@Data
@NoArgsConstructor
public class StaffRegisterRequest {
    
    @Digits(fraction = 0, integer = 1)
    private int id;

    @NotBlank (message = "name is required")
    @Length(min = 2, max = 50)
    private String name;

    @NotBlank (message = "address is required")
    private String address;

    @NotBlank (message = "phone number is required")
    private String phoneNumber;

    @NotBlank (message = "date of birth is required")
    private String dateOfBirth;
}
