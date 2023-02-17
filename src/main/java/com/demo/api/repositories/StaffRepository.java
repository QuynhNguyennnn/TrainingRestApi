package com.demo.api.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.api.entities.StaffEntity;
import com.demo.api.repositories.mapper.StaffMapper;

/**
 * Staff Repository.
 * 
 * @author QuynhNN
 */
@Repository
public class StaffRepository {

    @Autowired
    private StaffMapper staffMapper;

    /**
     * Get list of staffs.
     * 
     * @return all staff list
     */
    public List<StaffEntity> getAllStaff(int itemByPage, int offset) {
        return this.staffMapper.selectAllStaff(itemByPage, offset);
    }

    /**
     * Get staff details by id.
     * 
     * @param id the id of the staff needs to get information
     * @return the staff information
     */
    public StaffEntity getStaffDetailsById(int id) {
        return this.staffMapper.selectStaffById(id);
    }

    /**
     * Update staff by id.
     * 
     * @param staff new data want to update
     * @param id    id of staff want to update
     */
    public void updateStaffById(StaffEntity staff, int id) {
        this.staffMapper.updateStaffById(staff, id);
    }

    /**
     * Delete staff by id.
     * 
     * @param id id of the staff want to delete
     */
    public void deleteStaff(int id) {
        this.staffMapper.deleteStaff(id);
    }

    /**
     * Create new staff.
     * 
     * @param staffEntity data of new staff
     * @param id          id of staff
     */
    public void insertNewStaff(StaffEntity staffEntity) {
        this.staffMapper.insertNewStaff(staffEntity);
    }

    /**
     * Check id exists or not.
     * 
     * @param id the id needs to check
     * @return true or false (exist or not)
     */
    public Boolean isIdExist(int id) {
        return this.staffMapper.selectStaffById(id) != null;
    }

    /**
     * Search staff by name and id.
     * 
     * @param name name of staff, could be any characters in name
     * @param id   id of staff
     * @return the staffs list coincide with input
     */
    public List<StaffEntity> searchStaff(StaffEntity entity) {
        return this.staffMapper.searchStaff(entity);
    }
}
