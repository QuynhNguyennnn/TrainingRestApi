package com.demo.api.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.api.model.Staff;
import com.demo.api.repositories.mapper.StaffMapper;

@Repository
public class StaffRepository {

    @Autowired
    private StaffMapper staffMapper;

    public List<Staff> findAll() {
        return this.staffMapper.findAll();
    }
}
