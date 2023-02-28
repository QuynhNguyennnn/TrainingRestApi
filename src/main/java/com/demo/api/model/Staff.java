package com.demo.api.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Staff.
 * 
 * @author QuynhNN
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff implements Serializable {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String dateOfBirth;
}
