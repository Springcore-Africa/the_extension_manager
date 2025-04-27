package com.oracleous.extention_manager.dto.requests.investorFarmerRetrieval;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Schema(description = "Request payload require to fetch AgriBusiness data")
public class InvestorFarmerRetrievalRequest {
    private String email ;
}
