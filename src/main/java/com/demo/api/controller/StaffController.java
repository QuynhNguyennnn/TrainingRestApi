package com.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.demo.api.model.response.StaffsResponse;
import com.demo.api.model.StaffSearch;
import com.demo.api.model.request.AuthRequest;
import com.demo.api.model.request.RefreshRequest;
import com.demo.api.model.request.StaffRequest;
import com.demo.api.model.response.JwtResponse;
import com.demo.api.model.response.StaffResponse;
import com.demo.api.service.JwtService;
import com.demo.api.service.StaffService;

/**
 * Staff Controller.
 * 
 * @author QuynhNN
 */
@RestController
@Validated
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private JwtService jwtService;


    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello is exception");
    }
    /**
     * Get details staff by id.
     * 
     * @param id the id of the staff in database
     * @return staff details
     */
    @GetMapping(value = "/getStaffDetailsById/{id}")
    public ResponseEntity<StaffResponse> getStaffDetailsById(@Valid @PathVariable int id) {
        return ResponseEntity.ok(staffService.getStaffDetailsById(id));
    }

    /**
     * Get all staffs in database and search staff by id or name.
     * 
     * @return all staff values
     */
    @GetMapping(value = "/getAll")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<StaffsResponse> getAllStaffs(@Valid @RequestBody StaffSearch staffSearch) {
        return ResponseEntity.ok(staffService.getAllStaff(staffSearch));
    }

    /**
     * Update the staff.
     * 
     * @param staffResponse data wants to update.
     */
    @PutMapping(value = "/update")
    public void updateStaffById(@Valid @RequestBody StaffRequest staffRequest) {
        staffService.updateStaffById(staffRequest);
    }

    /**
     * Delete a staff from data.
     * 
     * @param id the staff id needs to delete
     */
    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public void deleteStaff(@PathVariable int id) {
        staffService.deleteStaff(id);
    }

    /**
     * insert new staff.
     * 
     * @param staffResponse save data returned from user.
     * @param id            new staff id.
     */
    @PostMapping(value = "/insert")
    @ResponseBody
    public void insertNewStaff(@Valid @RequestBody StaffRequest staffRequest) {
        staffService.insertNewStaff(staffRequest);
    }

    /**
     * Authenticate user.
     * 
     * @param authRequest username and password
     * @return token to access in api
     */
    @PostMapping(value = "/authenticate")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(jwtService.checkValidUsername(authRequest));
    }

    /**
     * Refresh token request.
     * 
     * @param refreshRequest refreshtoken and username
     * @return new access token and refresh token 
     */
    @GetMapping(value = "/refreshtoken")
    public ResponseEntity<JwtResponse> refreshedAuthenticate(@Valid @RequestBody RefreshRequest refreshRequest) {
        return ResponseEntity.ok(jwtService.checkValidRefreshToken(refreshRequest));
    }
}
