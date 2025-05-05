package com.oracleous.extention_manager.dto.requests.loginRequest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    private String email ;
    private String password ;
}
