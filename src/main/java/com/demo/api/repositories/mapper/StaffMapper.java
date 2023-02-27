package com.demo.api.repositories.mapper;

import com.demo.api.entities.StaffEntity;
import com.demo.api.entities.UserInfo;
import com.demo.api.model.StaffSearch;

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

    List<StaffEntity> selectAllStaff(@Param("staffSearch") StaffSearch staffSearch);

    StaffEntity selectStaffById(@Param("id") int id);

    void updateStaffById(StaffEntity staffEntity, int id);

    void deleteStaff(@Param("id") int id);

    void insertNewStaff(@Param("staffEntity") StaffEntity staffEntity);

    int searchStaff(@Param("staffEntity") StaffEntity staffEntity);

    UserInfo selectByUsername(@Param("username") String username);

}
