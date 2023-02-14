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
     * Get list of staffs
     * 
     * @return all staff list
     */
    public List<StaffEntity> getAll() {
        return this.staffMapper.selectAllStaff();
    }

    /**
     * get staff details by id
     * 
     * @param id the id of the staff needs to get information
     * @return the staff information
     */
    public StaffEntity getStaffDetailsById(int id){
        return this.staffMapper.selectStaffById(id);
    }
    
    /**
     *  update staff by id.
     * 
     * @param staff new data want to update 
     * @param id id of staff want to update
     */
    public void updateStaffById(StaffEntity staff, int id) {
        this.staffMapper.updateStaffById(staff, id);
    }

    /**
     * Delete staff by id.
     * 
     * @param id id of the staff want to delete
     */
    public void deleteStaff(int id){
        this.staffMapper.deleteStaff(id);
    }

    /**
     * create new staff.
     * 
     * @param staffEntity data of new staff
     * @param id id of staff
     */
    public void insertNewStaff(StaffEntity staffEntity, int id){
        this.staffMapper.insertNewStaff(staffEntity, id);
    }

    public Boolean findById(int id){
        if (this.staffMapper.selectStaffById(id) != null) {
            return true;
        } else {
            return false;
        }
    }
}
