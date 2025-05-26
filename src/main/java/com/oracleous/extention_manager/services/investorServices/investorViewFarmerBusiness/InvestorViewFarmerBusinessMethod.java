package com.oracleous.extention_manager.services.investorServices.investorViewFarmerBusiness;

import com.oracleous.extention_manager.data.model.*;
import com.oracleous.extention_manager.data.repositories.FarmersRepository;
import com.oracleous.extention_manager.data.repositories.InvestorRepository;
import com.oracleous.extention_manager.dto.response.investorAgriBusinessResponse.InvestorViewAgriBusinessResponse;
import com.oracleous.extention_manager.dto.response.investorFarmerRetrieval.InvestorViewFarmerResponse;
import com.oracleous.extention_manager.exceptions.InvestorNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.oracleous.extention_manager.utilities.ApplicationUtilities.INVESTOR_NOT_FOUND;
import static com.oracleous.extention_manager.utilities.ApplicationUtilities.USER_NOT_FOUND;

@Service
@AllArgsConstructor
//@NoArgsConstructor
@Slf4j
public class InvestorViewFarmerBusinessMethod implements InvestorViewFarmerBusiness {
    private final InvestorRepository investorRepository;
    private final FarmersRepository farmerRepository;

    @Override
    public InvestorViewAgriBusinessResponse getAgriBusinessByFarmer(Long farmerId) {
        Users user = getUsers();
        log.info("Users: {}", user);

        Optional<Investor> investor = investorRepository.findByUsers(user);
        if (investor.isEmpty()) {
            log.error("No Investor found for user: {}", user.getEmail());
            throw new InvestorNotFoundException(INVESTOR_NOT_FOUND);
        }
        log.info("Investor: {}", investor.get());

        Optional<Farmer> farmer = farmerRepository.findById(farmerId);
        if (farmer.isEmpty()) {
            log.error("No Farmer found with ID: {}", farmerId);
            throw new IllegalStateException("Farmer not found");
        }
        log.info("Farmer: {}", farmer.get());

        AgriBusiness agriBusiness = farmer.get().getAgriBusiness();
        if (agriBusiness == null) {
            log.warn("No AgriBusiness found for farmer ID: {}", farmerId);
            throw new IllegalStateException("AgriBusiness not found for farmer");
        }
        log.info("AgriBusiness: {}", agriBusiness);

        return InvestorViewAgriBusinessResponse.builder()
                .businessName(agriBusiness.getBusinessName())
                .businessLocationLga(agriBusiness.getBusinessLocationLga())
                .businessLocationState(agriBusiness.getBusinessLocationState())
                .agriculturalProduct(agriBusiness.getAgriculturalProduct())
                .farmPhotos(agriBusiness.getFarmPhotos())
                .productDescription(agriBusiness.getProductDescription())
                .businessLocationExact(agriBusiness.getBusinessLocationExact())
                .productPhotos(agriBusiness.getProductPhotos())
                .build();
    }
    private static @NotNull Users getUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user found");
        }
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Users users = userPrincipal.users();
        if (users == null) {
            throw new IllegalArgumentException(USER_NOT_FOUND);
        }
        return users;
    }
}
