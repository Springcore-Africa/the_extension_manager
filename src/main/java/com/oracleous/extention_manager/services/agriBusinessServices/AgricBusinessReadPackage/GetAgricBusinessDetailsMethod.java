package com.oracleous.extention_manager.services.agriBusinessServices.AgricBusinessReadPackage;

import com.oracleous.extention_manager.data.model.AgriBusiness;
import com.oracleous.extention_manager.data.model.Farmer;
import com.oracleous.extention_manager.data.model.UserPrincipal;
import com.oracleous.extention_manager.data.model.Users;
import com.oracleous.extention_manager.data.repositories.AgriBusinessRepository;
import com.oracleous.extention_manager.data.repositories.FarmersRepository;
import com.oracleous.extention_manager.dto.response.readResponse.AgricGetResponse;
import com.oracleous.extention_manager.exceptions.FarmerNotFoundExceptionWhileFetching;
import com.oracleous.extention_manager.utilities.ApplicationUtilities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import static com.oracleous.extention_manager.utilities.ApplicationUtilities.*;

@Service
@Builder
@AllArgsConstructor
@Slf4j
public class GetAgricBusinessDetailsMethod implements GetAgricBusinessDetails {
    private final AgriBusinessRepository agriBusinessRepository;
    private final FarmersRepository farmersRepository;

    @Override
    public AgricGetResponse getAgricBusinessDetails() {
        Users users = ApplicationUtilities.getCurrentUser();
        Farmer farmer = farmersRepository.findByUsers(users).orElseThrow(() -> new FarmerNotFoundExceptionWhileFetching(USER_NOT_FOUND_MESSAGE));
        AgriBusiness agriBusiness = farmer.getAgriBusiness();
        if (agriBusiness == null) {
            return AgricGetResponse.builder()
                    .responseMessage(AGRIBUSINESS_NOT_FOUND_MESSAGE)
                    .build();
        }
        return AgricGetResponse.builder()
                .businessLocationLga(agriBusiness.getBusinessLocationLga())
                .businessName(agriBusiness.getBusinessName())
                .businessLocationState(agriBusiness.getBusinessLocationState())
                .businessLocationExact(agriBusiness.getBusinessLocationExact())
                .cacNumber(agriBusiness.getCacNumber())
                .cacRegistrationDate(agriBusiness.getCacRegistrationDate())
                .regNumber(agriBusiness.getRegNumber())
                .farmSize(agriBusiness.getFarmSize())
                .agriculturalProduct(agriBusiness.getAgriculturalProduct())
                .yearlyProduction(agriBusiness.getYearlyProduction())
                .numberOfEmployees(agriBusiness.getNumberOfEmployees())
                .productDescription(agriBusiness.getProductDescription())
                .cacCertificate(agriBusiness.getCacCertificate())
                .memart(agriBusiness.getMemart())
                .farmerId(farmer.getId())
                .farmPhotos(agriBusiness.getFarmPhotos())
                .productPhotos(agriBusiness.getProductPhotos())
                .build();
    }
}