package com.oracleous.extention_manager.dto.requests.investorFarmerRetrieval;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Request payload require to fetch AgriBusiness data")
public class InvestorViewFarmerRequest {
    private String email ;
}
