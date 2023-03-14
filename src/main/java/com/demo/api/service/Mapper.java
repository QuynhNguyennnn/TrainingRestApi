package com.demo.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.demo.api.entities.*;
import com.demo.api.model.response.StaffResponse;

/**
 * Mapper.
 * 
 * @author QuynhNN
 */
public class Mapper {
    private List<StaffEntity> entity = new ArrayList<>();

    public Mapper(List<StaffEntity> staffEntities) {
        this.entity = staffEntities;
    }

    /**
     * to convert a list of Staff Entity to Staff Response.
     * 
     * @return a list of Staff Response
     */
    public List<StaffResponse> map() {
        return entity.stream()
                .map(i -> new StaffResponse(i.getId(), i.getName(),
                        i.getAddress(), i.getPhoneNumber(), i.getDateOfBirth(), i.getCreateUser(),
                        i.getCreateDateTime(), i.getUpdateUser(), i.getUpdateDateTime()))
                .collect(Collectors.toList());
    }
}
