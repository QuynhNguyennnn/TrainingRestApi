package com.demo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.api.entities.StaffEntity;
import com.demo.api.model.response.StaffResponse;
import com.demo.api.model.response.StaffsResponse;
import com.demo.api.repositories.StaffRepository;

/**
 * Staff Service.
 * 
 * @author QuynhNN
 */
@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    /**
     * get all staff list.
     * 
     * @return staffs response list.
     */
    public StaffsResponse getAllStaff() {
        List<StaffEntity> staffList = this.staffRepository.getAll();
        Mapper mapper = new Mapper(staffList);
        StaffsResponse response = new StaffsResponse();
        response.setStaffs(mapper.map());
        return response;
    }

    /**
     * get staff details by id.
     * 
     * @param id the id of the staff.
     * @return staff's information.
     */
    public StaffResponse getStaffDetailsById(int id){
        StaffEntity staffEntity = this.staffRepository.getStaffDetailsById(id);
        StaffResponse staffResponse = new StaffResponse();
        staffResponse.setId(staffEntity.getId());
        staffResponse.setName(staffEntity.getName());
        staffResponse.setAddress(staffEntity.getAddress());
        staffResponse.setPhoneNumber(staffEntity.getPhoneNumber());
        staffResponse.setDateOfBirth(staffEntity.getDateOfBirth());
        return staffResponse;
    }

}