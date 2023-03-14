package com.demo.api.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import java.util.Locale;

import static com.demo.api.constants.ErrorCode.ID_NOT_EXISTS;
import static com.demo.api.constants.ErrorCode.ID_EXISTS;
import static com.demo.api.constants.ErrorMessage.ID_NOT_FOUND;
import static com.demo.api.constants.ErrorMessage.ID_ALREADY_EXIST;
import com.demo.api.entities.StaffEntity;
import com.demo.api.errors.ApiError;
import com.demo.api.exceptions.ConflictException;
import com.demo.api.exceptions.IdNotFoundException;
import com.demo.api.model.StaffSearch;
import com.demo.api.model.request.StaffRequest;
import com.demo.api.model.response.StaffResponse;
import com.demo.api.model.response.StaffsResponse;
import com.demo.api.repositories.StaffRepository;

/**
 * Staff Service.
 * 
 * @author QuynhNN
 */
@Service
public class StaffService {

    private static Logger logger = LoggerFactory.getLogger(StaffService.class);

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private MessageSource messageSource;
    
    /**
     * Get staff list by page number.
     * 
     * @param staffSearch page number, number of item on page, id and name of staff
     * @return list of staff in that page
     */
    public StaffsResponse getAllStaff(StaffSearch staffSearch) {
        staffSearch.setOffset((staffSearch.getPage() - 1) * staffSearch.getItemByPage());
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setId(staffSearch.getId());
        staffEntity.setName(staffSearch.getName());
        List<StaffEntity> staffs = this.staffRepository.getAllStaff(staffSearch);
        Mapper mapper = new Mapper(staffs);
        StaffsResponse response = new StaffsResponse();
        response.setItemCount(this.staffRepository.searchStaff(staffEntity));
        response.setStaffs(mapper.map());
        return response;
    }

    /**
     * Get staff details by id.
     * 
     * @param id the id of the staff.
     * @return staff's information.
     */

    public StaffResponse getStaffDetailsById(int id) {
        if (!staffRepository.isIdExist(id)) {
            logger.error(messageSource.getMessage(ID_NOT_FOUND, null, Locale.ENGLISH));
            throw new IdNotFoundException(new ApiError(ID_NOT_EXISTS,
                    messageSource.getMessage(ID_NOT_FOUND, null, Locale.ENGLISH)));
        }
        StaffEntity staffEntity = this.staffRepository.getStaffDetailsById(id);
        StaffResponse staffResponse = new StaffResponse();
        staffResponse.setId(staffEntity.getId());
        staffResponse.setName(staffEntity.getName());
        staffResponse.setAddress(staffEntity.getAddress());
        staffResponse.setPhoneNumber(staffEntity.getPhoneNumber());
        staffResponse.setDateOfBirth(staffEntity.getDateOfBirth());
        return staffResponse;
    }

    /**
     * Update staff by id.
     * 
     * @param staff new information of staff
     * @param id    the staff id
     * @throws NoSuchStaffExistException
     */
    public void updateStaffById(StaffRequest staffRegisterRequest) {
        if (!staffRepository.isIdExist(staffRegisterRequest.getId())) {
            logger.error(messageSource.getMessage(ID_NOT_FOUND, null, Locale.ENGLISH));
            throw new IdNotFoundException(new ApiError(ID_NOT_EXISTS,
                    messageSource.getMessage(ID_NOT_FOUND, null, Locale.ENGLISH)));
        }
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setId(staffRegisterRequest.getId());
        staffEntity.setName(staffRegisterRequest.getName());
        staffEntity.setAddress(staffRegisterRequest.getAddress());
        staffEntity.setPhoneNumber(staffRegisterRequest.getPhoneNumber());
        staffEntity.setDateOfBirth(staffRegisterRequest.getDateOfBirth());
        this.staffRepository.updateStaffById(staffEntity, staffEntity.getId());
    }

    /**
     * Delete staff by id.
     * 
     * @param id id of the staff
     */
    public void deleteStaff(int id) {
        if (!staffRepository.isIdExist(id)) {
            logger.error(messageSource.getMessage(ID_NOT_FOUND, null, Locale.ENGLISH));
            throw new IdNotFoundException(new ApiError(ID_NOT_EXISTS,
                    messageSource.getMessage(ID_NOT_FOUND, null, Locale.ENGLISH)));
        }
        this.staffRepository.deleteStaff(id);
    }

    /**
     * Create/ insert new staff.
     * 
     * @param staffRequest information of staff
     */
    public void insertNewStaff(StaffRequest staffRequest) {
        if (staffRepository.isIdExist(staffRequest.getId())) {
            logger.error(messageSource.getMessage(ID_ALREADY_EXIST, null, Locale.ENGLISH));
            throw new ConflictException(new ApiError(ID_EXISTS,
                    messageSource.getMessage(ID_ALREADY_EXIST, null, Locale.ENGLISH)));
        }
        StaffEntity entity = new StaffEntity();
        entity.setId(staffRequest.getId());
        entity.setName((staffRequest.getName()));
        entity.setAddress(staffRequest.getAddress());
        entity.setPhoneNumber(staffRequest.getPhoneNumber());
        entity.setDateOfBirth(staffRequest.getDateOfBirth());
        this.staffRepository.insertNewStaff(entity);
    }
}