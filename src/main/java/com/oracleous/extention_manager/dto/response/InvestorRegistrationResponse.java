package com.oracleous.extention_manager.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvestorRegistrationResponse {
    private String responseMessage;
    private String FirstName;
    private String LastName;
}
