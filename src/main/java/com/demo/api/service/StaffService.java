package com.demo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.api.entities.StaffEntity;
import com.demo.api.errors.ApiError;
import com.demo.api.exceptions.BadRequestException;
import com.demo.api.model.request.PageRequest;
import com.demo.api.model.request.StaffRequest;
import com.demo.api.model.request.StaffSearchRequest;
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
     * Get staff list by page number.
     * 
     * @param pageRequest page number and number of item on page
     * @return list of staff in that page
     */
    public StaffsResponse getAllStaff(PageRequest pageRequest, String id, String name) {
        int page = pageRequest.getPage();
        int item = pageRequest.getItemByPage();
        int offset = 0;
        List<StaffEntity> staffs;
        if (id != null || name != null) {
            StaffEntity staffEntity = new StaffEntity();
            staffEntity.setId(Integer.parseInt(id));
            staffEntity.setName(name);
            staffs = this.staffRepository.searchStaff(staffEntity);
        } else {
            if (page == 1) {
                offset = 0;
                item = 10;
                staffs = this.staffRepository.getAllStaff(item, offset);
            } else {
                offset = (page - 1) * item;
                staffs = this.staffRepository.getAllStaff(item, offset);
            }
        }
        Mapper mapper = new Mapper(staffs);
        StaffsResponse response = new StaffsResponse();
        response.setStaffs(mapper.map());
        return response;
    }

    /**
     * Get staff details by id.
     * 
     * @param id the id of the staff.
     * @return staff's information.
     */
    public StaffResponse getStaffDetailsById(int id) {
        if (!staffRepository.isIdExist(id)) {
            throw new BadRequestException(new ApiError("id_not_found", "id_not_found"));
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
    public void updateStaffById(StaffRequest staffRegisterRequest) {
        if (!staffRepository.isIdExist(staffRegisterRequest.getId())) {
            throw new BadRequestException(new ApiError("id_not_found", "ID not found."));
        }
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setId(staffRegisterRequest.getId());
        staffEntity.setName(staffRegisterRequest.getName());
        staffEntity.setAddress(staffRegisterRequest.getAddress());
        staffEntity.setPhoneNumber(staffRegisterRequest.getPhoneNumber());
        staffEntity.setDateOfBirth(staffRegisterRequest.getDateOfBirth());
        this.staffRepository.updateStaffById(staffEntity, staffEntity.getId());
    }

    /**
     * Delete staff by id.
     * 
     * @param id id of the staff
     */
    public void deleteStaff(int id) {
        if (!staffRepository.isIdExist(id)) {
            throw new BadRequestException(new ApiError("id_not_found", "ID not found."));
        }
        this.staffRepository.deleteStaff(id);
    }

    /**
     * Create/ insert new staff.
     * 
     * @param staffRegisterRequest information of staff
     */
    public void insertNewStaff(StaffRequest staffRegisterRequest) {
        if (staffRepository.isIdExist(staffRegisterRequest.getId())) {
            throw new BadRequestException(new ApiError("id_already_exists", "ID already exists."));
        }
        StaffEntity entity = new StaffEntity();
        entity.setId(staffRegisterRequest.getId());
        entity.setName((staffRegisterRequest.getName()));
        entity.setAddress(staffRegisterRequest.getAddress());
        entity.setPhoneNumber(staffRegisterRequest.getPhoneNumber());
        entity.setDateOfBirth(staffRegisterRequest.getDateOfBirth());
        this.staffRepository.insertNewStaff(entity);
    }

    /**
     * Search staff by id and name.
     * 
     * @param searchRequest id and name of staff
     * @return the staffs list coincide with input
     */
    public StaffsResponse searchStaff(StaffSearchRequest searchRequest) {
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setId(searchRequest.getId());
        staffEntity.setName(searchRequest.getName());
        List<StaffEntity> staffList = this.staffRepository.searchStaff(staffEntity);
        Mapper mapper = new Mapper(staffList);
        StaffsResponse response = new StaffsResponse();
        response.setStaffs(mapper.map());
        return response;
    }

}