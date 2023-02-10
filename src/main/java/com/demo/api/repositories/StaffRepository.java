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
    
    public void updateStaffById(StaffEntity staff, int id) {
        this.staffMapper.updateStaffById(staff, id);
    }

    public void deleteStaff(int id){
        this.staffMapper.deleteStaff(id);
    }

    public void insertNewStaff(StaffEntity staffE, int id){
        this.staffMapper.insertNewStaff(staffE, id);
    }
}
