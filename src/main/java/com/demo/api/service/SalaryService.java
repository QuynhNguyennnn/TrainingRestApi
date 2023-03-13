package com.demo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.api.entities.SalaryEntity;
import com.demo.api.repositories.SalaryRepository;

@Service
public class SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    public List<SalaryEntity> findAllSalary(){
        return this.salaryRepository.findAllSalary();
    }
    
    public List<SalaryEntity> addInfoSalary(){
        return this.salaryRepository.addInfoSalary();
    }
}
