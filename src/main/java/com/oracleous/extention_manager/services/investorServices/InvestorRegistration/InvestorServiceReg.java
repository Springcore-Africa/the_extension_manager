package com.oracleous.extention_manager.services.investorServices.InvestorRegistration;

import com.oracleous.extention_manager.dto.requests.registrationRequest.InvestorRegistrationRequest;
import com.oracleous.extention_manager.dto.response.registrationResponse.InvestorRegistrationResponse;
import org.springframework.stereotype.Service;

@Service
public interface InvestorServiceReg {
    InvestorRegistrationResponse investorRegistration(InvestorRegistrationRequest investorRegistrationRequest);
}
