package com.demo.api.model.response;


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
