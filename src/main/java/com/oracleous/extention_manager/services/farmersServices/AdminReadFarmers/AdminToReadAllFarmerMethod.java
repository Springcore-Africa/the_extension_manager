package com.oracleous.extention_manager.services.farmersServices.AdminReadFarmers;

import com.oracleous.extention_manager.data.model.Admin;
import com.oracleous.extention_manager.data.model.Farmer;
import com.oracleous.extention_manager.data.model.UserPrincipal;
import com.oracleous.extention_manager.data.model.Users;
import com.oracleous.extention_manager.data.repositories.AdminRepository;
import com.oracleous.extention_manager.data.repositories.FarmersRepository;
import com.oracleous.extention_manager.dto.requests.adminViewFarmer.AdminViewFarmersRequest;
import com.oracleous.extention_manager.dto.response.adminToViewFarmers.AdminViewFarmersResponse;
import com.oracleous.extention_manager.exceptions.FarmerNotFoundExceptionWhileFetching;
import com.oracleous.extention_manager.utilities.ApplicationUtilities;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.oracleous.extention_manager.utilities.ApplicationUtilities.*;


@Service
@Slf4j
@AllArgsConstructor
public class AdminToReadAllFarmerMethod implements AdminToReadAllFarmer{

    private final AdminRepository adminRepository;
    private final FarmersRepository farmersRepository;

    @Override
    public AdminViewFarmersResponse adminViewFarmers(AdminViewFarmersRequest adminViewFarmersRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException(NO_AUTH_USER_FOUND);
        }
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Users users = userPrincipal.users();
        Admin admin = adminRepository.findByUsers(users);
        if(admin == null) {throw new IllegalArgumentException(USER_NOT_FOUND);}
        Farmer farmer = farmersRepository.findByEmailOrPhoneNumber(
                adminViewFarmersRequest.getEmail(),
                adminViewFarmersRequest.getPhoneNumber()
        ).orElseThrow(()-> new FarmerNotFoundExceptionWhileFetching(USER_NOT_FOUND_MESSAGE));
        return AdminViewFarmersResponse.builder()
                .lastName(farmer.getLastName())
                .firstName(farmer.getFirstName())
                .agriBusiness(farmer.getAgriBusiness())
                .lastEducationalCertificate(farmer.getLastEducationalCertificate())
                .gender(farmer.getGender())
                .maritalStatus(farmer.getMaritalStatus())
                .lgaOfOrigin(farmer.getLgaOfOrigin())
                .nationalId(farmer.getNationalId())
                .ninSlip(farmer.getNinSlip())
                .numberOfChildren(farmer.getNumberOfChildren())
                .passportPhotograph(farmer.getPassportPhotograph())
                .description(farmer.getDescription())
                .regNumber(farmer.getRegNumber())
                .residentialAddress(farmer.getResidentialAddress())
                .stateOfOrigin(farmer.getStateOfOrigin())
                .birthCertificate(farmer.getBirthCertificate())
                .build();
    }
}
