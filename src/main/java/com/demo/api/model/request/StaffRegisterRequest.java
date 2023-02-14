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

    @NotBlank (message = "name is mandatory")
    @Length(min = 2, max = 50)
    private String name;

    @NotBlank (message = "address is mandatory")
    private String address;

    @NotBlank (message = "phone number is mandatory")
    private String phoneNumber;

    @NotBlank (message = "date of birth is mandatory")
    private String dateOfBirth;
}
