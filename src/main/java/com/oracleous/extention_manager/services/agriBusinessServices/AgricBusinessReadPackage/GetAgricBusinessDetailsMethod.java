package com.oracleous.extention_manager.services.agriBusinessServices.AgricBusinessReadPackage;

import com.oracleous.extention_manager.data.repositories.AgriBusinessRepository;
import com.oracleous.extention_manager.data.repositories.FarmersRepository;
import com.oracleous.extention_manager.dto.requests.readRequest.AgricGetRequest;
import com.oracleous.extention_manager.dto.response.readResponse.AgricGetResponse;
import com.oracleous.extention_manager.exceptions.FarmerNotFoundExceptionWhileFetching;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Builder
@AllArgsConstructor
//@NoArgsConstructor
public class GetAgricBusinessDetailsMethod implements GetAgricBusinessDetails{
    @Autowired
    private AgriBusinessRepository agriBusinessRepository ;
    @Autowired
    private FarmersRepository farmersRepository ;

    @Override
    public AgricGetResponse getAgricBusinessDetails(AgricGetRequest getAgriBusinessDetailsRequest) {
        String email = getAgriBusinessDetailsRequest.getEmail();
        String phoneNumber = getAgriBusinessDetailsRequest.getPhoneNumber();

        farmersRepository.findByEmailAndPhoneNumber(email, phoneNumber).
                orElseThrow(()-> new FarmerNotFoundExceptionWhileFetching("FARMER NOT FOUND"));
        return null;
    }
}
