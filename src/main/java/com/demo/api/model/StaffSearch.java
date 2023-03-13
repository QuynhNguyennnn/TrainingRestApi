package com.demo.api.model;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Staff Search.
 * 
 * @author QuynhNN
 */
@Data
@NoArgsConstructor
@Component
public class StaffSearch {

    private Integer id;

    private String name;

    @NotNull(message = "Page number is required")
    private Integer page;

    @NotNull(message = "Item by page is required")
    private Integer itemByPage;

    private int offset;
}
