package com.oracleous.extention_manager.services.agriBusinessServices.AgricBusinessReadPackage;

import com.oracleous.extention_manager.data.model.AgriBusiness;
import com.oracleous.extention_manager.data.repositories.AgriBusinessRepository;
import com.oracleous.extention_manager.data.repositories.FarmersRepository;
import com.oracleous.extention_manager.dto.requests.readRequest.AgricGetRequest;
import com.oracleous.extention_manager.dto.response.readResponse.AgricGetResponse;
import com.oracleous.extention_manager.exceptions.FarmerNotFoundException;
import com.oracleous.extention_manager.exceptions.FarmerNotFoundExceptionWhileFetching;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.oracleous.extention_manager.utilities.ApplicationUtilities.*;

import java.util.Optional;

@Service
@Builder
@AllArgsConstructor
//@NoArgsConstructor
public class GetAgricBusinessDetailsMethod implements GetAgricBusinessDetails {
    @Autowired
    private AgriBusinessRepository agriBusinessRepository;
    @Autowired
    private FarmersRepository farmersRepository;

    @Override
    public AgricGetResponse getAgricBusinessDetails(AgricGetRequest getAgriBusinessDetailsRequest) {

//        boolean farmerExist = farmersRepository.existsByEmailOrPhoneNumber(
//                getAgriBusinessDetailsRequest.getEmail(),
//                getAgriBusinessDetailsRequest.getPhoneNumber()
//        );
//        if (farmerExist) {
//            Optional<AgriBusiness> agriBusiness = agriBusinessRepository.findByEmailOrPhoneNumber(
//                    getAgriBusinessDetailsRequest.getEmail(),
//                    getAgriBusinessDetailsRequest.getPhoneNumber()
//            );
        String email = getAgriBusinessDetailsRequest.getEmail();
        String phoneNumber = getAgriBusinessDetailsRequest.getPhoneNumber();

        // Ensure that at least one field is provided
        if ((email == null || email.isEmpty()) && (phoneNumber == null || phoneNumber.isEmpty())) {
            throw new IllegalArgumentException("Either Email or PhoneNumber must be provided.");
        }

        // Fetch AgriBusiness through associated Farmer
        Optional<AgriBusiness> agriBusiness = agriBusinessRepository.findByFarmer_EmailOrFarmer_PhoneNumber(email, phoneNumber);
            return agriBusiness.map(business -> AgricGetResponse.builder().
                    businessLocationLga(business.getBusinessLocationLga()).
                    businessName(business.getBusinessName()).
                    businessLocationState(business.getBusinessLocationState()).
                    businessLocationLga(business.getBusinessLocationLga()).
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
//        }
//        return AgricGetResponse.builder().
//                responseMessage(AGRIBUSINESS_NOT_FOUND_MESSAGE).
//                build();
    }
}
