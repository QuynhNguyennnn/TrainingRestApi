package com.demo.api.model.request;

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
    private int id;
    private String name;
    private String address;
    private String phoneNum;
    private String dateOfBirth;

}
