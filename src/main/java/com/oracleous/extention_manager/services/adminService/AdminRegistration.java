package com.oracleous.extention_manager.services.adminService;

import com.oracleous.extention_manager.dto.requests.requestEmail.AdminRegistrationRequestDto;
import com.oracleous.extention_manager.dto.response.ResponseToMailSend.InitiateAdminRegistration;
import com.oracleous.extention_manager.dto.requests.requestEmail.AdminCompletionRequestDto;

public interface AdminRegistration {
    InitiateAdminRegistration initiateAdminRegistration(AdminRegistrationRequestDto adminRegistrationRequestDto, String superAdminEmail);
    void completeAdminRegistration(String email, AdminCompletionRequestDto request);
}