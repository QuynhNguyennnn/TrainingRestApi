package com.demo.api.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.api.model.*;
import com.demo.api.service.StaffService;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    // test method
    @GetMapping(value = "/get")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("test");
    }

    // get all staffs
    @GetMapping(value = "/staffs")
    public List<Staff> getAllStaffs() {
        return staffService.findAll();
    }
}

