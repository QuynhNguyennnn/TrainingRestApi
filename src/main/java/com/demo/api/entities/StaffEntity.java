package com.demo.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Staff Entity.
 * 
 * @author QuynhNN
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffEntity {

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
