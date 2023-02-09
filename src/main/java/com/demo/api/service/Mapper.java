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

    Mapper(List<StaffEntity> eList) {
        this.entity = eList;
    }

    /**
     * to convert a list of Staff Entity to Staff Response.
     * 
     * @return a list of Staff Response
     */
    public List<StaffResponse> map() {
        return entity.stream()
                .map(i -> new StaffResponse(i.getId(), i.getName(),
                        i.getAddress(), i.getPhoneNumber(), i.getDateOfBirth()))
                .collect(Collectors.toList());
    }
}
