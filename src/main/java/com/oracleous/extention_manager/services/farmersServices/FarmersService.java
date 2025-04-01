package com.oracleous.extention_manager.services.farmersServices;

import com.oracleous.extention_manager.data.model.Farmer;
import com.oracleous.extention_manager.dto.requests.FarmersRegistrationRequest;
import com.oracleous.extention_manager.dto.response.FarmerResponse;

public interface FarmersService {
    FarmerResponse registerFarmer(FarmersRegistrationRequest farmersRegistrationRequest);
}
