package com.oracleous.extention_manager.services.farmersServices.FarmerReadPackage;

import com.oracleous.extention_manager.data.model.Farmer;
import com.oracleous.extention_manager.data.model.Gender;
import com.oracleous.extention_manager.data.model.MaritalStatus;
import com.oracleous.extention_manager.data.repositories.FarmersRepository;
import com.oracleous.extention_manager.dto.requests.readRequest.FarmerGetRequest;
import com.oracleous.extention_manager.dto.response.readResponse.FarmerGetResponse;
import com.oracleous.extention_manager.dto.response.readResponse.FullName;
import com.oracleous.extention_manager.exceptions.FarmerNotFoundExceptionWhileFetching;
import com.oracleous.extention_manager.exceptions.InvestorNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetFarmerDetailsMethod implements GetFarmerDetails {
    private final FarmersRepository farmersRepository;

    @Override
    public FarmerGetResponse getFarmerDetails(FarmerGetRequest getFarmerDetailsRequest) {
        String email = getFarmerDetailsRequest.getEmail();
        String phoneNumber = getFarmerDetailsRequest.getPhoneNumber();

        if ((email == null || email.isEmpty()) && (phoneNumber == null || phoneNumber.isEmpty())) {
            throw new FarmerNotFoundExceptionWhileFetching("Either Email or PhoneNumber must be provided");
        }

        Farmer farmer = farmersRepository.findByEmailOrPhoneNumber(email,phoneNumber).
                orElseThrow(()-> new FarmerNotFoundExceptionWhileFetching("Farmer not found"));

        FullName fullName = FullName.builder().
                firstName(farmer.getFirstName()).
                lastName(farmer.getLastName()).
                build();

        return FarmerGetResponse.builder().
                fullName(fullName).
                phoneNumber(farmer.getPhoneNumber()).
                email(farmer.getEmail()).
                nationalId(farmer.getNationalId()).
                dateOfBirth(farmer.getDateOfBirth()).
                stateOfOrigin(farmer.getStateOfOrigin()).
                lgaOfOrigin(farmer.getLgaOfOrigin()).
                residentialAddress(farmer.getResidentialAddress()).
                numberOfChildren(farmer.getNumberOfChildren()).
                regNumber(farmer.getRegNumber()).
                description(farmer.getDescription()).
                ninSlip(farmer.getNinSlip()).
                birthCertificate(farmer.getBirthCertificate()).
                lastEducationalCertificate(farmer.getLastEducationalCertificate()).
                passportPhotograph(farmer.getPassportPhotograph()).
                maritalStatus((MaritalStatus) farmer.getMaritalStatus()).
                gender((Gender) farmer.getGender()).
                stateOfOrigin(farmer.getStateOfOrigin()).
                dateOfBirth(farmer.getDateOfBirth()).
                password(farmer.getPassword())
                .build();

    }
}
