package com.oracleous.extention_manager.services.localAdminService.localAdminRegistration;

import com.oracleous.extention_manager.dto.requests.registrationRequest.LocalAdminRegRequest;
import com.oracleous.extention_manager.dto.response.registrationResponse.LocalAdminRegResponse;

public interface LocalAdminService {
    LocalAdminRegResponse localAdminRegistration(LocalAdminRegRequest localAdminRegRequest);
}
