package com.demo.api.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class SalaryModel implements Serializable{
    private int id;
    private int id_staff;
    private int month;
    private int year;
    private BigDecimal salary_base;
    private BigDecimal salary_net;


    public SalaryModel (){}


    public SalaryModel(int id, int id_staff, int month, int year, BigDecimal salary_base, BigDecimal salary_net) {
        this.id = id;
        this.id_staff = id_staff;
        this.month = month;
        this.year = year;
        this.salary_base = salary_base;
        this.salary_net = salary_net;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getId_staff() {
        return id_staff;
    }


    public void setId_staff(int id_staff) {
        this.id_staff = id_staff;
    }


    public int getMonth() {
        return month;
    }


    public void setMonth(int month) {
        this.month = month;
    }


    public int getYear() {
        return year;
    }


    public void setYear(int year) {
        this.year = year;
    }


    public BigDecimal getSalary_base() {
        return salary_base;
    }


    public void setSalary_base(BigDecimal salary_base) {
        this.salary_base = salary_base;
    }


    public BigDecimal getSalary_net() {
        return salary_net;
    }


    public void setSalary_net(BigDecimal salary_net) {
        this.salary_net = salary_net;
    }



    
    
}
