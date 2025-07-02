package com.oracleous.extention_manager.services.adminService.completeRegistration;

import com.oracleous.extention_manager.dto.requests.requestEmail.AdminCompletionRequestDto;
import com.oracleous.extention_manager.dto.response.ResponseToMailSend.CompleteAdminRegistration;

public interface CompleteAdminReg {
    CompleteAdminRegistration completeAdminRegistration(AdminCompletionRequestDto request);
}
