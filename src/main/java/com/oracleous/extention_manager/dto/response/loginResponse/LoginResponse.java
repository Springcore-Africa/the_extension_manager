package com.oracleous.extention_manager.dto.response.loginResponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String token;
    private String message ;
}
