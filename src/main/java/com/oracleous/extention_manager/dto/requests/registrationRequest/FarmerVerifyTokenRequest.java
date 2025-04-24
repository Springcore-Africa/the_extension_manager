package com.oracleous.extention_manager.dto.requests.registrationRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
    public class FarmerVerifyTokenRequest {
    private String token;
}
