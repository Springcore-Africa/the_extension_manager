package com.oracleous.extention_manager.services.farmersServices.FarmerReadPackage;

import com.oracleous.extention_manager.data.model.Farmer;
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

        if (email.isEmpty() || phoneNumber.isEmpty()) {
            throw new InvestorNotFoundException("Email or PhoneNumber must not be null");
        }
        Farmer farmer = farmersRepository.findByEmailAndPhoneNumber(email,phoneNumber).
                orElseThrow(()-> new FarmerNotFoundExceptionWhileFetching("Farmer not found"));

        FullName fullName = FullName.builder().
                firstName(farmer.getFirstName()).
                lastName(farmer.getLastName()).
                build();

        return FarmerGetResponse.builder().
                fullName(fullName).
                phoneNumber(farmer.getPhoneNumber()).
                email(farmer.getEmail()).
                build();
    }
}
