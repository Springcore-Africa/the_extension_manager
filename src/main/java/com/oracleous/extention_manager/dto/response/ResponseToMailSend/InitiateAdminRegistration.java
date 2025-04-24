package com.oracleous.extention_manager.dto.response.ResponseToMailSend;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InitiateAdminRegistration {
    private String message;
}
