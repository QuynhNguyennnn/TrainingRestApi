package com.demo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.api.entities.StaffEntity;
import com.demo.api.repositories.StaffRepository;

/**
 * StaffService
 * 
 * @author QuynhNN
 */
@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public List<StaffEntity> getAllStaff() {
        return this.staffRepository.getAll();
    }

    public StaffEntity getStaffDetailsById(int id){
        return this.staffRepository.getStaffDetailsById(id);
    }

    public void updateStaffById(StaffEntity staff, int id) {
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setName(staff.getName());
        staffEntity.setAddress(staff.getAddress());
        staffEntity.setPhoneNumber(staff.getPhoneNumber());
        staffEntity.setDateOfBirth(staff.getDateOfBirth());
        this.staffRepository.updateStaffById(staffEntity, id);
    }
}