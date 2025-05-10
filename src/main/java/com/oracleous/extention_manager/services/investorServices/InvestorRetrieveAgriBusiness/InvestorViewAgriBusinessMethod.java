package com.oracleous.extention_manager.services.investorServices.InvestorRetrieveAgriBusiness;

import com.oracleous.extention_manager.data.model.Investor;
import com.oracleous.extention_manager.data.repositories.AgriBusinessRepository;
import com.oracleous.extention_manager.data.repositories.InvestorRepository;
import com.oracleous.extention_manager.dto.requests.investorAgriBusinessRetrieval.InvestorAgriBusinessRetrievalRequest;
import com.oracleous.extention_manager.dto.response.investorAgriBusinessResponse.InvestorAgriBusinessRetrievalResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class InvestorViewAgriBusinessMethod implements  InvestorViewAgriBusiness{
    private final InvestorRepository investorRepository;
    private final AgriBusinessRepository agriBusinessRepository;


    @Override
    public InvestorAgriBusinessRetrievalResponse agriBusinessResponse(InvestorAgriBusinessRetrievalRequest request) {
        Optional<Investor> investor = investorRepository.findByUsersEmailOrPhoneNumber(request.getEmail(), request.getPhoneNumber());
            if(investor.isEmpty())
                return InvestorAgriBusinessRetrievalResponse.builder().
                        message("Investor not found ").
                        build();
//        AgriBusiness agriBusiness = agriBusinessRepository.findByFarmerId()
        return null;
    }

}
