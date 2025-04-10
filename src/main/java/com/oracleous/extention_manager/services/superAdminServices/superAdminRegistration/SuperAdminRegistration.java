package com.oracleous.extention_manager.services.superAdminServices.superAdminRegistration;

import com.oracleous.extention_manager.dto.requests.SuperAdminRegRequest;
import com.oracleous.extention_manager.dto.response.SuperAdminResponse;
import org.springframework.stereotype.Service;

@Service
public interface SuperAdminRegistration {
    SuperAdminResponse superAdminRegistration(SuperAdminRegRequest superAdminRegRequest);
}
