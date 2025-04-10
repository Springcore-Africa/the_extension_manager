package com.oracleous.extention_manager.services.adminService;

import com.oracleous.extention_manager.dto.requests.SuperAdminRegRequest;
import com.oracleous.extention_manager.dto.response.SuperAdminResponse;

public interface AdminRegistration {
    SuperAdminResponse superAdminRegistration(SuperAdminRegRequest superAdminRegRequest);
}
