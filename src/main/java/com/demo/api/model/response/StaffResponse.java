package com.demo.api.model.response;


import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Staff Response.
 * 
 * @author QuynhNN
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class StaffResponse {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String dateOfBirth;
    private String createUser;
    private String createDateTime;
    private String updateUser;
    private String updateDateTime;
}
