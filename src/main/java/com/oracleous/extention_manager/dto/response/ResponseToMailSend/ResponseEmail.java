package com.oracleous.extention_manager.dto.response.ResponseToMailSend;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseEmail {
    private String message;
}
