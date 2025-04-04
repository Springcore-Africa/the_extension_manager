package com.oracleous.extention_manager.services.farmersServices.FarmerReadPackage;

import com.oracleous.extention_manager.dto.requests.readRequest.FarmerGetRequest;
import com.oracleous.extention_manager.dto.response.readResponse.FarmerGetResponse;

public interface GetFarmerDetails {
    FarmerGetResponse getFarmerDetails(FarmerGetRequest getFarmerDetailsRequest);
}
