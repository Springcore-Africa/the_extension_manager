package com.oracleous.extention_manager.services.agriBusinessServices.AgricBusinessReadPackage;

import com.oracleous.extention_manager.dto.requests.readRequest.AgricGetRequest;
import com.oracleous.extention_manager.dto.response.readResponse.AgricGetResponse;

public interface GetAgricBusinessDetails {
    AgricGetResponse getAgricBusinessDetails(AgricGetRequest getAgricBusinessDetailsRequest);
}
