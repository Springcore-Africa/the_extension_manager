package com.oracleous.extention_manager.services.farmersServices.FarmerRegistration;

import com.oracleous.extention_manager.dto.requests.registrationRequest.FarmersRegistrationRequest;
import com.oracleous.extention_manager.dto.response.registrationResponse.FarmerResponse;

public interface FarmersService {
    FarmerResponse registerFarmer(FarmersRegistrationRequest farmersRegistrationRequest, String tokenProvided);
}
