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

    /**
     * Update staff by id.
     * 
     * @param staff new information of staff
     * @param id the staff id
     */
    public void updateStaffById(StaffResponse staff, int id) {
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setName(staff.getName());
        staffEntity.setAddress(staff.getAddress());
        staffEntity.setPhoneNumber(staff.getPhoneNumber());
        staffEntity.setDateOfBirth(staff.getDateOfBirth());
        this.staffRepository.updateStaffById(staffEntity, id);
    }

    public void deleteStaff(int id){
        this.staffRepository.deleteStaff(id);
    }

    public void insertNewStaff(StaffResponse staffResponse, int id){
        StaffEntity entity = new StaffEntity();
        entity.setId(staffResponse.getId());
        entity.setName((staffResponse.getName()));
        entity.setAddress(staffResponse.getAddress());
        entity.setPhoneNumber(staffResponse.getPhoneNumber());
        entity.setDateOfBirth(staffResponse.getDateOfBirth());
        this.staffRepository.insertNewStaff(entity, id);
    }

}