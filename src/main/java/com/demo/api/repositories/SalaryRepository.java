package com.demo.api.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.api.entities.SalaryEntity;
import com.demo.api.repositories.mapper.SalaryMapper;

@Repository
public class SalaryRepository {
    @Autowired
    private SalaryMapper salaryMapper;

    public List<SalaryEntity> findAllSalary(){
        return this.salaryMapper.findAllSalary();
    }

    public List<SalaryEntity> addInfoSalary(){
        return this.salaryMapper.findAllSalary();
    }
}
