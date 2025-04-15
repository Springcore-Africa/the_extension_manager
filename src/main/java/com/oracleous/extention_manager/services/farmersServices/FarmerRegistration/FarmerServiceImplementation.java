package com.oracleous.extention_manager.services.farmersServices.FarmerRegistration;

import com.oracleous.extention_manager.data.model.Farmer;
import com.oracleous.extention_manager.data.repositories.FarmersRepository;
import com.oracleous.extention_manager.dto.requests.registrationRequest.FarmersRegistrationRequest;
import com.oracleous.extention_manager.dto.response.registrationResponse.FarmerInfo;
import com.oracleous.extention_manager.dto.response.registrationResponse.FarmerResponse;
import com.oracleous.extention_manager.utilities.ApplicationUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.oracleous.extention_manager.utilities.ApplicationUtilities.*;

@Service
@RequiredArgsConstructor
public class FarmerServiceImplementation implements FarmersService {

    final FarmersRepository farmersRepository;

    public FarmerResponse registerFarmer(FarmersRegistrationRequest farmersRegistrationRequest)  {

        boolean farmerExists = farmersRepository.existsByEmailOrNationalIdOrPhoneNumber(
                farmersRegistrationRequest.getEmail(),
                farmersRegistrationRequest.getNationalId(),
                farmersRegistrationRequest.getPhoneNumber()
        );

        if (farmerExists) {
            return FarmerResponse.builder()
                    .responseCode(FARMER_EXIST_CODE)
                    .responseMessage(FARMER_ALREADY_EXIST)
                    .farmerInfo(null)
                    .build();
        }

        Farmer newFarmer = Farmer.builder()
                .email(farmersRegistrationRequest.getEmail())
                .nationalId(farmersRegistrationRequest.getNationalId())
                .phoneNumber(farmersRegistrationRequest.getPhoneNumber())
                .firstName(farmersRegistrationRequest.getFirstName())
                .lastName(farmersRegistrationRequest.getLastName())
                .dateOfBirth(farmersRegistrationRequest.getDateOfBirth())
                .gender(farmersRegistrationRequest.getGender())
                .description(farmersRegistrationRequest.getDescription())
                .lgaOfOrigin(farmersRegistrationRequest.getLgaOfOrigin())
                .maritalStatus(farmersRegistrationRequest.getMaritalStatus())
                .numberOfChildren(farmersRegistrationRequest.getNumberOfChildren())
                .regNumber(ApplicationUtilities.generateRegNumber())
                .residentialAddress(farmersRegistrationRequest.getResidentialAddress())
                .ninSlip(farmersRegistrationRequest.getNinSlip())
                .passportPhotograph(farmersRegistrationRequest.getPassportPhotograph())
                .lastEducationalCertificate(farmersRegistrationRequest.getLastEducationalCertificate())
                .birthCertificate(farmersRegistrationRequest.getBirthCertificate())
                .build();
        Farmer savedFarmer = farmersRepository.save(newFarmer);

        return FarmerResponse.builder()
                .responseCode(ACCOUNT_CREATED_CODE)
                .responseMessage(ACCOUNT_CREATED_MESSAGE)
                .farmerInfo(FarmerInfo.builder()
                        .farmersName(savedFarmer.getFirstName() +" "+ savedFarmer.getLastName())
                        .farmersRegNumber(savedFarmer.getRegNumber())
                        .build())
                .build();



    }

}
