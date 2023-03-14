package com.demo.api.model.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Staff Register Request.
 * 
 * @author QuynhNN 
 */
@Data
@NoArgsConstructor
public class StaffRequest {
    
    @Digits(fraction = 0, integer = 2)
    private int id;

    @NotNull(message = "name is required")
    @Length(min = 2, max = 50)
    private String name;

    @NotNull (message = "address is required")
    private String address;

    @NotNull (message = "phone number is required")
    private String phoneNumber;

    @NotNull (message = "date of birth is required")
    private String dateOfBirth;
}
