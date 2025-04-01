package com.oracleous.extention_manager.services.agriBusinessServices;

import com.oracleous.extention_manager.data.model.AgriBusiness;
import com.oracleous.extention_manager.data.model.Farmer;
import com.oracleous.extention_manager.data.repositories.AgriBusinessRepository;
import com.oracleous.extention_manager.data.repositories.FarmersRepository;
import com.oracleous.extention_manager.dto.requests.AgriBusinessRegRequest;
import com.oracleous.extention_manager.dto.response.AgriBusinessInfo;
import com.oracleous.extention_manager.dto.response.AgriBusinessResponse;
import com.oracleous.extention_manager.exceptions.BusinessAlreadyExistsException;
import com.oracleous.extention_manager.exceptions.FarmerNotFoundException;
import com.oracleous.extention_manager.utilities.ApplicationUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.oracleous.extention_manager.utilities.ApplicationUtilities.BUSINESS_REGISTERED_CODE;
import static com.oracleous.extention_manager.utilities.ApplicationUtilities.BUSINESS_REGISTERED_MESSAGE;

@Service
@RequiredArgsConstructor
public class AgriBusinessImpl implements AgriBusinessService {
    final AgriBusinessRepository agriBusinessRepository;
    final FarmersRepository farmersRepository;

    @Override
    public AgriBusinessResponse registerAgriBusiness(AgriBusinessRegRequest regRequest) throws BusinessAlreadyExistsException, FarmerNotFoundException {
        Farmer farmer = farmersRepository.findById(regRequest.getFarmerId())
                .orElseThrow(() -> new FarmerNotFoundException("Farmer not found"));


        if (agriBusinessRepository.existsByCacNumber(regRequest.getCacNumber())) {
            throw new BusinessAlreadyExistsException("Business with CAC number already exists.");
        }

        AgriBusiness agriBusiness = AgriBusiness.builder()
                .businessName(regRequest.getBusinessName())
                .businessLocationState(regRequest.getBusinessLocationState())
                .businessLocationLga(regRequest.getBusinessLocationLga())
                .businessLocationExact(regRequest.getBusinessLocationExact())
                .cacNumber(regRequest.getCacNumber())
                .cacRegistrationDate(regRequest.getCacRegistrationDate())
                .farmSize(regRequest.getFarmSize())
                .agriculturalProduct(regRequest.getAgriculturalProduct())
                .yearlyProduction(regRequest.getYearlyProduction())
                .numberOfEmployees(regRequest.getNumberOfEmployees())
                .productDescription(regRequest.getProductDescription())
                .cacCertificate(regRequest.getCacCertificate())
                .memart(regRequest.getMemart())
                .farmPhotos(regRequest.getFarmPhotos())
                .productPhotos(regRequest.getProductPhotos())
                .regNumber(ApplicationUtilities.generateBusRegNumber())
                .farmer(farmer)
                .build();


        AgriBusiness savedBusiness = agriBusinessRepository.save(agriBusiness);


        return AgriBusinessResponse.builder()
                .responseCode(BUSINESS_REGISTERED_CODE)
                .responseMessage(BUSINESS_REGISTERED_MESSAGE)
                .agriBusinessInfo(AgriBusinessInfo.builder()
                        .businessName(savedBusiness.getBusinessName())
                        .businessRgeNumber(savedBusiness.getRegNumber())
                        .farmersName(farmer.getFirstName() + " " + farmer.getLastName())
                        .build())

                .build();
    }

    public List<AgriBusiness> getBusinessesByFarmer(Long farmerId) {
        return agriBusinessRepository.findByFarmerId(farmerId);
    }

}
