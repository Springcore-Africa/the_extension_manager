package com.oracleous.extention_manager.services.investorServices.InvestorRetrieveAgriBusiness;

import com.oracleous.extention_manager.data.model.AgriBusiness;
import com.oracleous.extention_manager.dto.requests.investorAgriBusinessRetrieval.InvestorAgriBusinessRetrievalRequest;
import org.springframework.stereotype.Service;

@Service
public interface InvestorViewAgriBusiness {
    AgriBusiness agriBusinessResponse();
}
