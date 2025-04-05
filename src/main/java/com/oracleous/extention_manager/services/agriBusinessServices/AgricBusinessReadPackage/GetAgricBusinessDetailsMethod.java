package com.oracleous.extention_manager.services.agriBusinessServices.AgricBusinessReadPackage;

import com.oracleous.extention_manager.data.model.AgriBusiness;
import com.oracleous.extention_manager.data.repositories.AgriBusinessRepository;
import com.oracleous.extention_manager.data.repositories.FarmersRepository;
import com.oracleous.extention_manager.dto.requests.readRequest.AgricGetRequest;
import com.oracleous.extention_manager.dto.response.readResponse.AgricGetResponse;
import com.oracleous.extention_manager.dto.response.readResponse.FullName;
import com.oracleous.extention_manager.exceptions.FarmerNotFoundException;
import com.oracleous.extention_manager.exceptions.FarmerNotFoundExceptionWhileFetching;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.oracleous.extention_manager.utilities.ApplicationUtilities.*;

import java.util.Optional;

@Service
@Builder
@AllArgsConstructor
@Slf4j
public class GetAgricBusinessDetailsMethod implements GetAgricBusinessDetails {
    @Autowired
    private AgriBusinessRepository agriBusinessRepository;
    @Autowired
    private FarmersRepository farmersRepository;

    @Override
    public AgricGetResponse getAgricBusinessDetails(AgricGetRequest getAgriBusinessDetailsRequest) {

        String email = getAgriBusinessDetailsRequest.getEmail();
        log.info("this is the email input: {} ", email);

        String phoneNumber = getAgriBusinessDetailsRequest.getPhoneNumber();
        log.info("this is the phone number: {}", phoneNumber);

        if ((email == null || email.isEmpty()) && (phoneNumber == null || phoneNumber.isEmpty())) {
            throw new IllegalArgumentException("Either Email or PhoneNumber must be provided.");
        }
        log.info("getAgricBusinessDetails email: {}, phoneNumber: {}", email, phoneNumber);

        Optional<AgriBusiness> agriBusiness = agriBusinessRepository.findByFarmer_EmailOrFarmer_PhoneNumber(email, phoneNumber);
            return agriBusiness.map(business -> AgricGetResponse.builder().fullName(FullName.builder().
                                    firstName(business.getFarmer().getFirstName()).
                                    lastName(business.getFarmer().getLastName()).
                                    build()).
                    email(business.getFarmer().getEmail()).
                    phoneNumber(business.getFarmer().getPhoneNumber()).

                    businessLocationLga(business.getBusinessLocationLga()).
                    businessName(business.getBusinessName()).
                    businessLocationState(business.getBusinessLocationState()).
                    businessLocationExact(business.getBusinessLocationExact()).
                    cacNumber(business.getCacNumber()).
                    cacRegistrationDate(business.getCacRegistrationDate()).
                    regNumber(business.getRegNumber()).
                    farmSize(business.getFarmSize()).
                    agriculturalProduct(business.getAgriculturalProduct()).
                    yearlyProduction(business.getYearlyProduction()).
                    numberOfEmployees(business.getNumberOfEmployees()).
                    productDescription(business.getProductDescription()).
                    cacCertificate(business.getCacCertificate()).
                    memart(business.getMemart()).
                    farmPhotos(business.getFarmPhotos()).
                    productPhotos(business.getProductPhotos()).
                    build()).
                    orElse
                            (AgricGetResponse.builder()
                                    .responseMessage(AGRIBUSINESS_NOT_FOUND_MESSAGE)
                                    .build());
    }
}
