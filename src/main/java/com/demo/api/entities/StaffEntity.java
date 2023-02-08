package com.demo.api.entities;

import lombok.Data;

/**
 * Staff Entity.
 * 
 * @author QuynhNN
 */
@Data
public class StaffEntity {

    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String dateOfBirth;
}
