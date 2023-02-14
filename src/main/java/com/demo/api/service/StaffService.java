package com.demo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.api.entities.StaffEntity;
import com.demo.api.errors.ApiError;
import com.demo.api.exceptions.IdNotFoundException;
import com.demo.api.exceptions.BadRequestException;
import com.demo.api.model.request.StaffRegisterRequest;
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
    public StaffResponse getStaffDetailsById(int id) {
        if (!staffRepository.findById(id)){
            throw new BadRequestException(new ApiError("id_not_found", "ID not found."));
        }
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
     * @param id    the staff id
     * @throws NoSuchStaffExistException
     */
    public void updateStaffById(StaffRegisterRequest staffRegisterRequest){
        if (!staffRepository.findById(staffRegisterRequest.getId())){
            throw new BadRequestException(new ApiError("id_not_found", "ID not found."));
        }

        int id = staffRegisterRequest.getId();
        if (!staffRepository.findById(id)) {
            throw new IdNotFoundException();
        } else {
            StaffEntity staffEntity = new StaffEntity();
            staffEntity.setId(staffRegisterRequest.getId());
            staffEntity.setName(staffRegisterRequest.getName());
            staffEntity.setAddress(staffRegisterRequest.getAddress());
            staffEntity.setPhoneNumber(staffRegisterRequest.getPhoneNumber());
            staffEntity.setDateOfBirth(staffRegisterRequest.getDateOfBirth());
            this.staffRepository.updateStaffById(staffEntity, staffEntity.getId());
        }
    }

    /**
     * delete staff by id.
     * 
     * @param id id of the staff
     */
    public void deleteStaff(int id) {
        if (!staffRepository.findById(id)){
            throw new BadRequestException(new ApiError("id_not_found", "ID not found."));
        }
        this.staffRepository.deleteStaff(id);
    }

    /**
     * create/ insert new staff.
     * 
     * @param staffResponse staff information
     * @param id            id of staff
     */
    public void insertNewStaff(StaffRegisterRequest staffRegisterRequest, int id) {
        if (staffRepository.findById(id)){
            throw new BadRequestException(new ApiError("id_already_exists", "ID already exists."));
        }
        StaffEntity entity = new StaffEntity();
        entity.setId(staffRegisterRequest.getId());
        entity.setName((staffRegisterRequest.getName()));
        entity.setAddress(staffRegisterRequest.getAddress());
        entity.setPhoneNumber(staffRegisterRequest.getPhoneNumber());
        entity.setDateOfBirth(staffRegisterRequest.getDateOfBirth());
        this.staffRepository.insertNewStaff(entity, id);
    }

}