package com.demo.api.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.api.mapper.StaffMapper;
import com.demo.api.model.Staff;

@Repository
public class StaffRepository {

    @Autowired
    private StaffMapper staffMapper;

    public List<Staff> findAll() {
        return this.staffMapper.findAll();
    }
}
