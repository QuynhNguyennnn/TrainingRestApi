package com.demo.api.repositories;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.demo.api.entities.StaffEntity;
import com.demo.api.repositories.mapper.StaffMapper;

/**
 * StaffRepository
 * 
 * @author QuynhNN
 */
@Repository
public class StaffRepository {

    @Autowired
    private StaffMapper staffMapper;

    public List<StaffEntity> getAll() {
        return this.staffMapper.selectAllStaff();
    }

    public StaffEntity getStaffDetailsById(int id){
        return this.staffMapper.selectStaffById(id);
    }

    public void updateStaffById(StaffEntity staff, int id) {
        this.staffMapper.updateStaffById(staff, id);
    }
}
