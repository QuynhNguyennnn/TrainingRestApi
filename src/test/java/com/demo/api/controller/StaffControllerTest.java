package com.demo.api.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.api.model.StaffSearch;
import com.demo.api.model.request.StaffRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Staff Controller Test.
 * 
 * @author QuynhNN
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@WithMockUser(username = "quynhnn", password = "123456")
@Transactional(propagation = Propagation.REQUIRED)
public class StaffControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Testing that application context is correctly configured.
     * 
     * @param context
     */
    @Test
    void contextLoads(ApplicationContext context) {
        assertTrue(context != null);
        ;
    }

    /**
     * Test get All Staff API.
     * 
     * @throws Exception
     */
    @Test
    public void getAllStaffAPI() throws Exception {
        StaffSearch staffSearch = new StaffSearch();
        staffSearch.setId(1);
        staffSearch.setName("quy");
        staffSearch.setPage(1);
        staffSearch.setItemByPage(10);
        staffSearch.setOffset((staffSearch.getPage() - 1) * staffSearch.getItemByPage());
        
        mockMvc.perform(MockMvcRequestBuilders
                .get("/staff/getAll")
                .content(asJsonString(staffSearch))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    /**
     * Map Object to String.
     * 
     * @param obj object
     * @return
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test get Staff by Id API.
     * 
     * @throws Exception
     */
    @Test
    public void getStaffByIdAPI() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/staff/getStaffDetailsById/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Quynh"))
                .andReturn();
    }

    /**
     * Test update Staff.
     * 
     * @throws Exception
     */
    @Test
    public void updateStaffAPI() throws Exception {
        StaffRequest staffRequest = new StaffRequest();
        staffRequest.setId(1);
        staffRequest.setName("QuynhNg");
        staffRequest.setAddress("CT");
        staffRequest.setPhoneNumber("012233");
        staffRequest.setDateOfBirth("2000");

        mockMvc.perform(MockMvcRequestBuilders
                .put("/staff/update")
                .content(asJsonString(staffRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    /**
     * Test delete Staff.
     * 
     * @throws Exception
     */
    @Test
    public void deleteStaffAPI() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/staff/delete/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Test insert Staff.
     * 
     * @throws Exception
     */
    @Test
    public void insertStaffAPI() throws Exception {
        StaffRequest staffRequest = new StaffRequest();
        staffRequest.setId(40);
        staffRequest.setName("QuynhNg");
        staffRequest.setAddress("CT");
        staffRequest.setPhoneNumber("012233");
        staffRequest.setDateOfBirth("2000");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/staff/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(staffRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
