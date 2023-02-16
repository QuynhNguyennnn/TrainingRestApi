package com.demo.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Staff Search Request.
 * 
 * @author QuynhNN
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffSearchRequest {
    private String name;
    private int id;
}
