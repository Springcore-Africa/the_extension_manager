package com.oracleous.extention_manager.services.agriBusinessServices;

import com.oracleous.extention_manager.dto.requests.AgriBusinessRegRequest;
import com.oracleous.extention_manager.dto.response.AgriBusinessResponse;
import com.oracleous.extention_manager.exceptions.BusinessAlreadyExistsException;
import com.oracleous.extention_manager.exceptions.FarmerNotFoundException;

public interface AgriBusinessService {

    AgriBusinessResponse registerAgriBusiness(AgriBusinessRegRequest agriBusinessRegRequest) throws BusinessAlreadyExistsException, FarmerNotFoundException;
}
