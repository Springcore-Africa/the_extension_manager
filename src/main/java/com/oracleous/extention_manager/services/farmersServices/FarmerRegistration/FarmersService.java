package com.oracleous.extention_manager.services.farmersServices.FarmerRegistration;

import com.oracleous.extention_manager.dto.requests.FarmersRegistrationRequest;
import com.oracleous.extention_manager.dto.response.FarmerResponse;

public interface FarmersService {
    FarmerResponse registerFarmer(FarmersRegistrationRequest farmersRegistrationRequest);
}
