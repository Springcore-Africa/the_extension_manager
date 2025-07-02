package com.oracleous.extention_manager.services.superAdminServices.superAdminRegistration;

import com.oracleous.extention_manager.dto.requests.registrationRequest.SuperAdminRegRequest;
import com.oracleous.extention_manager.dto.response.registrationResponse.SuperAdminResponse;
import org.springframework.stereotype.Service;

@Service
public interface SuperAdminRegistration {
    SuperAdminResponse superAdminRegistration(SuperAdminRegRequest superAdminRegRequest);
}