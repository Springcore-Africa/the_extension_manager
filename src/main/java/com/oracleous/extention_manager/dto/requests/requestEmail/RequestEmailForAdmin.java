package com.oracleous.extention_manager.dto.requests.requestEmail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RequestEmailForAdmin {
    private String email;
}
