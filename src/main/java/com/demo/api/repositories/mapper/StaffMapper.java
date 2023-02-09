package com.demo.api.repositories.mapper;

import com.demo.api.entities.StaffEntity;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Staff Mapper
 * 
 * @author QuynhNN
 */
@Mapper
public interface StaffMapper {

    List<StaffEntity> selectAllStaff();
    StaffEntity selectStaffById(@Param("id") int id);

}
