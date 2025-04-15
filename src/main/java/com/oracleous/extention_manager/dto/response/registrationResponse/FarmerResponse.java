package com.oracleous.extention_manager.dto.response.registrationResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmerResponse {
    private String responseCode;
    private String responseMessage;
    private FarmerInfo farmerInfo;
}
