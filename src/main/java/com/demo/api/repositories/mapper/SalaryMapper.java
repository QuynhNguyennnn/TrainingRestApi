package com.demo.api.repositories.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.demo.api.entities.SalaryEntity;

@Mapper
public interface SalaryMapper {

    /**
     * @return
     */
    @Select("select * from salary")
    public List<SalaryEntity> findAllSalary();

    // @Select("Select * from salary where id = #{id}")
    // public Salary findByIdSalary(int id);

    // @Select("delect from salary where id = #{id}")
    // public int deleteSalaryById(int id);

    @Select("Insert into salary values (id, id_staff, month, year, salary_base, tax, salary_net) values (#{id}, #{id_staff}, #{month}, #{year}, #{salary_base}, #{tax}, #{salary_net})")
    public int addInfoSalary(SalaryEntity newSala);

    // @Select("Update salary set id = #{id}, id_staff = #{id_staff}, month = #{month}, year = #{year}, salary_base = #{salary_base}, tax = #{tax}, salary_net = #{salary_net} where id_staff = #{id_staff}")
    // public int updateInfoSalary(Salary salary);
}
