package com.oracleous.extention_manager.dto.response.registrationResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AgriBusinessResponse {
    private String responseCode;
    private String responseMessage;
    private AgriBusinessInfo agriBusinessInfo;
}
