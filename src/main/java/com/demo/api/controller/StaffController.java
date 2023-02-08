package com.demo.api.controller;

import java.util.*;

// import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


import com.demo.api.entities.StaffEntity;
import com.demo.api.service.StaffService;


/**
 * StaffController
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
     * @param id the id of the staff in database
     * @return staff details
     */
    @GetMapping(value = "/getStaffDetailsById/{id}")
    public StaffEntity getStaffDetailsById(@PathVariable int id) {
        return staffService.getStaffDetailsById(id);
    }
    
    /**
     * Get all staffs in database
     * @return all staff
     */
    @GetMapping(value = "/getAll")
    public List<StaffEntity> getAllStaffs() {
        return staffService.getAllStaff();
    }

    /**
     * update a staff by id
     */
    @GetMapping(value="/update/{id}")
    public void updateStaffById(@PathVariable(value = "id") int id, @RequestBody StaffEntity entity) {
        staffService.updateStaffById(entity, id);
    }
    
}
