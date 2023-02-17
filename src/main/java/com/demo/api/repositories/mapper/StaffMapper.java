package com.demo.api.repositories.mapper;

import com.demo.api.entities.StaffEntity;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Staff Mapper
 * 
 * @author QuynhNN
 */
@Mapper
public interface StaffMapper {

    List<StaffEntity> selectAllStaff(@Param("page") int page, @Param("itemByPage") int itemBypage);

    StaffEntity selectStaffById(@Param("id") int id);

    void updateStaffById(StaffEntity staffEntity, int id);

    void deleteStaff(@Param("id") int id);

    void insertNewStaff(StaffEntity staffEntity);

    List<StaffEntity> searchStaff(@Param("staffEntity") StaffEntity staffEntity);
}
