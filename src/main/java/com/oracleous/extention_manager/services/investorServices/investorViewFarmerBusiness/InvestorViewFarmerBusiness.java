package com.oracleous.extention_manager.services.investorServices.investorViewFarmerBusiness;

import com.oracleous.extention_manager.dto.response.investorAgriBusinessResponse.InvestorViewAgriBusinessResponse;
import com.oracleous.extention_manager.dto.response.investorFarmerRetrieval.InvestorViewFarmerResponse;
import org.springframework.stereotype.Service;

public interface InvestorViewFarmerBusiness {
    InvestorViewAgriBusinessResponse getAgriBusinessByFarmer(Long farmerId);
}
