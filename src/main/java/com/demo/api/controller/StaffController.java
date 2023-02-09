package com.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.api.model.response.StaffsResponse;
import com.demo.api.model.response.StaffResponse;

import com.demo.api.service.StaffService;

/**
 * Staff Controller.
 * 
 * @author QuynhNN
 */
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    /**
     * Get details staff by id.
     * 
     * @param id the id of the staff in database
     * @return staff details
     */
    @GetMapping(value = "/getStaffDetailsById/{id}")
    public ResponseEntity<StaffResponse> getStaffDetailsById(@PathVariable int id) {
        
        return ResponseEntity.ok(staffService.getStaffDetailsById(id));
    }

    /**
     * Get all staffs in database.
     * 
     * @return all staff
     */
    @GetMapping(value = "/getAll")
    public ResponseEntity<StaffsResponse> getAllStaffs() {
         return ResponseEntity.ok(staffService.getAllStaff());
    }


}
