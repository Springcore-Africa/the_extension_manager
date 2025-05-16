package com.oracleous.extention_manager.services.investorServices.InvestorRetrieveAgriBusiness;

import com.oracleous.extention_manager.data.model.*;
import com.oracleous.extention_manager.data.repositories.FarmersRepository;
import com.oracleous.extention_manager.data.repositories.InvestorRepository;
import com.oracleous.extention_manager.exceptions.FarmerNotFoundException;
import com.oracleous.extention_manager.exceptions.InvestorNotFoundException;
import com.oracleous.extention_manager.utilities.ApplicationUtilities;
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
@Slf4j
@AllArgsConstructor
public class InvestorViewAgriBusinessMethod implements  InvestorViewAgriBusiness{
    private final InvestorRepository investorRepository;
    private final FarmersRepository farmersRepository;

    @Override
    public AgriBusiness agriBusinessResponse() {
        Users users = ApplicationUtilities.getCurrentUser();
        log.info("users here{}", users);
        Optional<Investor> investor = investorRepository.findByUsers(users);
        if(investor.isEmpty()){
            throw new InvestorNotFoundException(INVESTOR_NOT_FOUND);
        }
        log.info("investor here {}", investor);

        Optional<Farmer> farmer = farmersRepository.findByUsers(users);
        if(farmer.isEmpty()){
            throw new IllegalStateException(USER_NOT_FOUND);
        }
        log.info("farmer here {}", farmer);

        AgriBusiness agricBusiness = farmer.get().getAgriBusiness();
        if(agricBusiness == null){
            throw new InvestorNotFoundException(USER_NOT_FOUND);
        }
        log.info("agricBusiness here {}", agricBusiness);
        return AgriBusiness.builder()
                .businessName(agricBusiness.getBusinessName())
                .businessLocationLga(agricBusiness.getBusinessLocationLga())
                .businessLocationState(agricBusiness.getBusinessLocationState())
                .agriculturalProduct(agricBusiness.getAgriculturalProduct())
                .farmPhotos(agricBusiness.getFarmPhotos())
                .productDescription(agricBusiness.getProductDescription())
                .businessLocationExact(agricBusiness.getBusinessLocationExact())
                .productPhotos(agricBusiness.getProductPhotos())
                .build();
    }


}