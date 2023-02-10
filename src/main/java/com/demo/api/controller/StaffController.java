package com.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
     * @return all staff values
     */
    @GetMapping(value = "/getAll")
    public ResponseEntity<StaffsResponse> getAllStaffs() {
         return ResponseEntity.ok(staffService.getAllStaff());
    }

    
    /**
     * Update the staff.
     * 
     * @param id the staff id needs to upate.
     * @param staffResponse data wants to update. 
     */
    @PutMapping(value = "/update/{id}")
    public void updateStaffById(@PathVariable(value = "id") int id, @RequestBody StaffResponse staffResponse) {
        staffService.updateStaffById(staffResponse, id);

    }

    /**
     * Delete a staff from data.
     * 
     * @param id the staff id needs to delete
     */
    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public void deleteStaff(@PathVariable int id){
        staffService.deleteStaff(id);
    }

    /**
     * insert new staff.
     * 
     * @param staffResponse save data returned from user.
     * @param id new staff id.
     */
    @PostMapping(value = "/insert/{id}")
    @ResponseBody
    public void insertNewStaff(@RequestBody StaffResponse staffResponse, @PathVariable(value = "id") int id){         
        staffService.insertNewStaff(staffResponse, id);
    }

}
