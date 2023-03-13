package com.demo.api.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.api.exceptions.BadRequestException;
import com.demo.api.exceptions.ConflictException;
import com.demo.api.exceptions.IdNotFoundException;
import com.demo.api.model.StaffSearch;
import com.demo.api.model.request.AuthRequest;
import com.demo.api.model.request.RefreshRequest;
import com.demo.api.model.request.StaffRequest;
import com.demo.api.model.response.JwtResponse;
import com.demo.api.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Staff Controller Test.
 * 
 * @author QuynhNN
 */
@SpringBootTest
@AutoConfigureMockMvc
// (addFilters = false)
@WithMockUser(username = "quynhnn", password = "123456")
@Transactional(propagation = Propagation.REQUIRED)
public class StaffControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    JwtService jwtService;

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

        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("quynhnn");
        authRequest.setPassword("123456");
        JwtResponse response = jwtService.checkValidUsername(authRequest);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/staff/update")
                .header("Authorization", response.getAccessToken())
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
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
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
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    /**
     * Test authenticate user.
     * 
     * @throws Exception
     */
    @Test
    public void authenticateAPI() throws Exception {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("quynhnn");
        authRequest.setPassword("123456");
        mockMvc.perform(MockMvcRequestBuilders
                .post("/staff/authenticate")
                .content(asJsonString(authRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    /**
     * Test id not found exception when get staff details by id.
     * 
     * @throws Exception
     */
    @Test
    public void getStaffByIdAPI_IdNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/staff/getStaffDetailsById/40")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IdNotFoundException))
                .andReturn();
    }

    /**
     * Test id exist excetption when insert staff with id is already exist.
     * 
     * @throws Exception
     */
    @Test
    public void insertStaffAPI_IdExistException() throws Exception {
        StaffRequest staffRequest = new StaffRequest();
        staffRequest.setId(1);
        staffRequest.setName("QuynhNg");
        staffRequest.setAddress("CT");
        staffRequest.setPhoneNumber("012233");
        staffRequest.setDateOfBirth("2000");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/staff/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(staffRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ConflictException))
                .andReturn();
    }

    /**
     * Test id not found exception when update staff with id is not exist.
     * 
     * @throws Exception
     */
    @Test
    public void updateStaffAPI_IdNotFoundException() throws Exception {
        StaffRequest staffRequest = new StaffRequest();
        staffRequest.setId(40);
        staffRequest.setName("QuynhNg");
        staffRequest.setAddress("CT");
        staffRequest.setPhoneNumber("012233");
        staffRequest.setDateOfBirth("2000");

        mockMvc.perform(MockMvcRequestBuilders
                .put("/staff/update")
                .content(asJsonString(staffRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IdNotFoundException))
                .andReturn();
    }

    /**
     * Test delete staff with id is not exists.
     * 
     * @throws Exception
     */
    @Test
    public void deleteStaffAPI_IdNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/staff/delete/40"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }

    /**
     * Test authenticate user with wrong username.
     * 
     * @throws Exception
     */
    @Test
    public void authenticateAPI_UsernameNotFoundException() throws Exception {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("quynh");
        authRequest.setPassword("123456");
        mockMvc.perform(MockMvcRequestBuilders
                .post("/staff/authenticate")
                .content(asJsonString(authRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UsernameNotFoundException))
                .andReturn();
    }

    /**
     * Test authenticate user with wrong password.
     * 
     * @throws Exception
     */
    @Test
    public void authenticateAPI_IncorrectPasswordException() throws Exception {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("quynhnn");
        authRequest.setPassword("12345");
        mockMvc.perform(MockMvcRequestBuilders
                .post("/staff/authenticate")
                .content(asJsonString(authRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadRequestException))
                .andReturn();
    }

    /**
     * Test refresh token.
     * 
     * @throws Exception
     */
    @Test
    public void refreshTokenAPI() throws Exception {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("quynhnn");
        authRequest.setPassword("123456");
        JwtResponse response = jwtService.checkValidUsername(authRequest);
        
        RefreshRequest refreshRequest = new RefreshRequest();
        refreshRequest.setRefreshToken(response.getRefreshToken());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/staff/refreshtoken")
                .content(asJsonString(refreshRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}
