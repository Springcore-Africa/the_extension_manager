package com.oracleous.extention_manager.services.investorServices.InvestorReadPackage;

import com.oracleous.extention_manager.dto.requests.readRequest.InvestorGetRequest;
import com.oracleous.extention_manager.dto.response.readResponse.InvestorGetResponse;

public interface GetInvestorDetails {
    InvestorGetResponse getInvestorDetails(InvestorGetRequest getInvestorDetailsRequest);
}
