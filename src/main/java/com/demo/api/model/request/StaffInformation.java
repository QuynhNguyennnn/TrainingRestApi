package com.demo.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffInformation {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String dateOfBirth;
}
