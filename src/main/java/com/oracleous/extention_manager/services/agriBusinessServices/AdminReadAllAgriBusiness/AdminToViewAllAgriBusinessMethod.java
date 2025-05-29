package com.oracleous.extention_manager.services.agriBusinessServices.AdminReadAllAgriBusiness;

import com.oracleous.extention_manager.data.model.Admin;
import com.oracleous.extention_manager.data.model.AgriBusiness;
import com.oracleous.extention_manager.data.model.Farmer;
import com.oracleous.extention_manager.data.model.Users;
import com.oracleous.extention_manager.data.repositories.AdminRepository;
import com.oracleous.extention_manager.data.repositories.AgriBusinessRepository;
import com.oracleous.extention_manager.data.repositories.FarmersRepository;
import com.oracleous.extention_manager.dto.response.AdminViewAgriBusiness.AdminViewAgriBusinessErrorMessage;
import com.oracleous.extention_manager.dto.response.AdminViewAgriBusiness.AdminViewAgriBusinessResponse;
import com.oracleous.extention_manager.dto.response.readResponse.AgricGetResponse;
import com.oracleous.extention_manager.exceptions.FarmerNotFoundExceptionWhileFetching;
import com.oracleous.extention_manager.utilities.ApplicationUtilities;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.oracleous.extention_manager.utilities.ApplicationUtilities.AGRIBUSINESS_NOT_FOUND_MESSAGE;
import static com.oracleous.extention_manager.utilities.ApplicationUtilities.USER_NOT_FOUND_MESSAGE;

@Service
@Slf4j
@AllArgsConstructor
public class AdminToViewAllAgriBusinessMethod implements AdminToViewAllAgriBusiness {
    private final AdminRepository adminRepository;
    private final FarmersRepository farmersRepository;
    private final AgriBusinessRepository agriBusinessRepository;

    @Override
    public AdminViewAgriBusinessResponse adminViewAgriBusinessResponse() {
        Users users = ApplicationUtilities.getCurrentUser();
        Admin admin = adminRepository.findByUsers(users);
        if (admin != null) {
            agriBusinessRepository.findAll();
        }
        Farmer farmer = farmersRepository.findByUsers(users).orElseThrow(() -> new FarmerNotFoundExceptionWhileFetching(USER_NOT_FOUND_MESSAGE));
        AgriBusiness agriBusiness = farmer.getAgriBusiness();
        if (agriBusiness == null) {
            throw new FarmerNotFoundExceptionWhileFetching(AGRIBUSINESS_NOT_FOUND_MESSAGE);
        }
        return AdminViewAgriBusinessResponse.builder()
                .businessName(agriBusiness.getBusinessName())
                .businessLocationState(agriBusiness.getBusinessLocationState())
                .build();
    }
}
