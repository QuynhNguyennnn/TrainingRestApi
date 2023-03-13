package com.demo.api.repositories.mapper;

import com.demo.api.model.*;
import java.util.*;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StaffMapper {

    @Select("select * from staff")
    public List<Staff> findAll();

    @Select("Select * from staff where id = #{id}")
    public Staff findById(int id);

    @Select("delect from staff where id = #{id}")
    public int deleteStaffById(int id);

    @Select("Insert into staff values (id, name, address, phoneNum, dateOfBirth) values (#{id}, #{name}, #{address}, #{phoneNum}, #{dateOfBirth})")
    public int addNewStaff(Staff staff);

    @Select("Update staff set name = #{name}, address = #{address}, phoneNum = #{phoneNum}, dateOfBirth = #{dateOfBirth} where id = #{id}")
    public int updateStaff(Staff staff);

}
