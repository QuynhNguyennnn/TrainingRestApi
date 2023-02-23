package com.demo.api.model;

import javax.validation.constraints.NotNull;

import com.demo.api.validators.IntegerConstraint;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Staff Search.
 * 
 * @author QuynhNN
 */
@Data
@NoArgsConstructor
public class StaffSearch {

    @IntegerConstraint
    private Integer id;

    private String name;

    @NotNull(message = "Page number is required")
    @IntegerConstraint
    private Integer page;

    @NotNull(message = "Item by page is required")
    @IntegerConstraint
    private Integer itemByPage;

    private int offset;
}
