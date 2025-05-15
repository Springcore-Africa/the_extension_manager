package com.oracleous.extention_manager.services.investorServices.investorRetrieveFarmerBusiness;

import com.oracleous.extention_manager.dto.response.investorFarmerRetrieval.InvestorViewFarmerResponse;
import org.springframework.stereotype.Service;

@Service
public interface InvestorViewFarmer {
    InvestorViewFarmerResponse retrieveFarmer();
}
