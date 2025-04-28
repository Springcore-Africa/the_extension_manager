package com.oracleous.extention_manager.services.investorServices.investorRetrieveFarmerBusiness;

import com.oracleous.extention_manager.dto.response.investorFarmerRetrieval.InvestorFarmerRetrievalResponse;
import org.springframework.stereotype.Service;

@Service
public interface InvestorViewFarmer {
    InvestorFarmerRetrievalResponse retrieveFarmer();
}
