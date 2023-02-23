package com.demo.api.model.response;

import java.util.List;

import lombok.Data;

/**
 * Staffs Response.
 * 
 * @author QuynhNN
 */
@Data
public class StaffsResponse {
    
    private int itemCount;
    private List<StaffResponse> staffs;
}
