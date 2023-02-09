package com.demo.api.model.response;

import lombok.Data;

@Data
public class StaffResponse {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String dateOfBirth;
}
