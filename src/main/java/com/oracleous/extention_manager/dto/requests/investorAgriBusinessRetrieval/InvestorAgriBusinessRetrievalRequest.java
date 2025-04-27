package com.oracleous.extention_manager.dto.requests.investorAgriBusinessRetrieval;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvestorAgriBusinessRetrievalRequest {
    private String email ;
    private String phoneNumber ;
}
