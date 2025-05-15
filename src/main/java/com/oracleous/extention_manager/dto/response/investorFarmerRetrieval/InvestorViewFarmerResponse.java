package com.oracleous.extention_manager.dto.response.investorFarmerRetrieval;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Request payload require to fetch AgriBusiness data")
public class InvestorViewFarmerResponse {
    private String firstName ;
    private String lastName ;
    private String residentialAddress ;
    private String picture ;
    private String shortDescription ;
    private String maritalStatus ;
}
