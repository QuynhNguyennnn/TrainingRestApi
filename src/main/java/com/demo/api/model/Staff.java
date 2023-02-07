package com.demo.api.model;

import java.io.Serializable;

public class Staff implements Serializable {
    private int id;
    private String name;
    private String address;
    private String phoneNum;
    private String dateofBirth;

    public Staff() {

    }

    public Staff(int id, String name, String address, String phoneNum, String dateofBirth) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.dateofBirth = dateofBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(String dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    @Override
    public String toString() {
        return "Staff [id=" + id + ", name=" + name + ", address=" + address + "phoneNum=" + phoneNum
                + ", dateofBirth=" + dateofBirth + "]";
    }

}
