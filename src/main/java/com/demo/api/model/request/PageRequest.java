package com.demo.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Page Request.
 * 
 * @author QuynhNN
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest {
    private int page;
    private int itemByPage;

}
