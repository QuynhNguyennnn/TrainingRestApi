package com.demo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.api.entities.SalaryEntity;
import com.demo.api.service.SalaryService;

@RestController
@RequestMapping("/salary")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;

    // get all salary
    @GetMapping(value = "/infosalary")
    public List<SalaryEntity> getAllInfoSalary() {
        return salaryService.findAllSalary();
    }

    //add info salary
    @ResponseBody
    @PostMapping(value = "/addInfoSalary")
    public String addInfoSalary(@RequestBody SalaryEntity newSala)
    {
        salaryService.addInfoSalary();
        return "Saved successfully!";
    }
}
