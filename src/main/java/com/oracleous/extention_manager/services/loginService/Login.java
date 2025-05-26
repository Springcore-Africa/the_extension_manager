package com.oracleous.extention_manager.services.loginService;

import com.oracleous.extention_manager.dto.requests.loginRequest.LoginRequest;
import com.oracleous.extention_manager.dto.response.loginResponse.LoginResponse;

public interface Login {
    LoginResponse loginResponse(LoginRequest loginRequest);
}
