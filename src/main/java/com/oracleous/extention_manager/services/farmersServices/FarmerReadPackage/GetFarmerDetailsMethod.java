package com.oracleous.extention_manager.services.farmersServices.FarmerReadPackage;

import com.oracleous.extention_manager.data.model.Farmer;
import com.oracleous.extention_manager.data.model.Gender;
import com.oracleous.extention_manager.data.model.MaritalStatus;
import com.oracleous.extention_manager.data.model.Users;
import com.oracleous.extention_manager.data.repositories.FarmersRepository;
import com.oracleous.extention_manager.dto.requests.readRequest.FarmerGetRequest;
import com.oracleous.extention_manager.dto.response.readResponse.FarmerGetResponse;
import com.oracleous.extention_manager.dto.response.readResponse.FullName;
import com.oracleous.extention_manager.exceptions.FarmerNotFoundExceptionWhileFetching;
import static com.oracleous.extention_manager.utilities.ApplicationUtilities.*;
import com.oracleous.extention_manager.exceptions.InvestorNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetFarmerDetailsMethod implements GetFarmerDetails {
    private final FarmersRepository farmersRepository;

    @Override
    public FarmerGetResponse getFarmerDetails(FarmerGetRequest getFarmerDetailsRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() instanceof String) {
            throw new IllegalArgumentException("User not found");
        }

        String email = getFarmerDetailsRequest.getEmail();
        String phoneNumber = getFarmerDetailsRequest.getPhoneNumber();

        if ((email == null || email.isEmpty()) && (phoneNumber == null || phoneNumber.isEmpty())) {
            throw new FarmerNotFoundExceptionWhileFetching(REQUIRED_REQUEST_MESSAGE);
        }

        Farmer farmer = farmersRepository.findByEmailOrPhoneNumber(email,phoneNumber).
                orElseThrow(()-> new FarmerNotFoundExceptionWhileFetching(USER_NOT_FOUND_MESSAGE));

        FullName fullName = FullName.builder().
                firstName(farmer.getFirstName()).
                lastName(farmer.getLastName()).
                build();

//        Users users = Users.builder()
//                .email(farmer.getEmail())
//                .build();

        return FarmerGetResponse.builder().
                fullName(fullName).
                phoneNumber(farmer.getPhoneNumber()).
                email(farmer.getUsers().getEmail()).
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
                dateOfBirth(farmer.getDateOfBirth())
//                password(farmer.getUsers().getPassword())
                .build();

    }
}
