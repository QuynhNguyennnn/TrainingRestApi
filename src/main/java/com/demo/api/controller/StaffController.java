package com.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import java.util.Locale;

import javax.validation.Valid;

import com.demo.api.model.response.StaffsResponse;
import static com.demo.api.constants.ErrorMessage.INSERT_SUCCESS;
import com.demo.api.model.StaffSearch;
import com.demo.api.model.request.StaffRequest;
import com.demo.api.model.response.ApiResponse;
import com.demo.api.model.response.StaffResponse;
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
    private MessageSource messageSource;

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
    public ResponseEntity<ApiResponse> insertNewStaff(@Valid @RequestBody StaffRequest staffRequest) {
        staffService.insertNewStaff(staffRequest);
        return new ResponseEntity<>(new ApiResponse(messageSource.getMessage(INSERT_SUCCESS, null, Locale.ENGLISH)),
                HttpStatus.CREATED);
    }
}
