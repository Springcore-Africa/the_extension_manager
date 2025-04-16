package com.oracleous.extention_manager.services.adminService.adminRegistration;

import com.oracleous.extention_manager.dto.requests.requestEmail.AdminRegistrationRequestDto;
import com.oracleous.extention_manager.dto.response.ResponseToMailSend.InitiateAdminRegistration;

public interface AdminRegistration {
    InitiateAdminRegistration initiateAdminRegistration(AdminRegistrationRequestDto adminRegistrationRequestDto, String superAdminEmail);
//    CompleteAdminRegistration completeAdminRegistration(AdminCompletionRequestDto request);

    boolean isAdminEmailRegistered(String email);
}