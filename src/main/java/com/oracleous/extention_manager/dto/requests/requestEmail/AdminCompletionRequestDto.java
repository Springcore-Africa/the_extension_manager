package com.oracleous.extention_manager.dto.requests.requestEmail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminCompletionRequestDto {
    private String token;
    private String email ;
    private String name;
    private String password;
}
