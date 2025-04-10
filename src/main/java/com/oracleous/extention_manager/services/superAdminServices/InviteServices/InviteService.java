package com.oracleous.extention_manager.services.superAdminServices.InviteServices;

import com.oracleous.extention_manager.dto.requests.requestEmail.RequestEmailForAdmin;
import com.oracleous.extention_manager.dto.response.ResponseToMailSend.ResponseEmail;
import org.springframework.stereotype.Service;

public interface InviteService {
    ResponseEmail responsetoAdmin(RequestEmailForAdmin requestEmailForAdmin);
}
