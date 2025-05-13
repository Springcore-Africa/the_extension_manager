package com.oracleous.extention_manager.services.investorServices.InvestorRetrieveAgriBusiness;

import com.oracleous.extention_manager.data.model.AgriBusiness;
import com.oracleous.extention_manager.data.model.Investor;
import com.oracleous.extention_manager.data.model.Users;
import com.oracleous.extention_manager.data.repositories.FarmersRepository;
import com.oracleous.extention_manager.data.repositories.InvestorRepository;
import com.oracleous.extention_manager.exceptions.InvestorNotFoundException;
import com.oracleous.extention_manager.utilities.ApplicationUtilities;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        if(users == null){
            throw new IllegalArgumentException(USER_NOT_FOUND);
        }
        Optional<Investor> investor = investorRepository.findByUsers(users);
        if(investor.isEmpty()){
            throw new InvestorNotFoundException(INVESTOR_NOT_FOUND);
        }
        AgriBusiness agricBusiness = farmersRepository.findByUsers(users).orElseThrow(() -> new IllegalArgumentException("User not found")).getAgriBusiness();
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