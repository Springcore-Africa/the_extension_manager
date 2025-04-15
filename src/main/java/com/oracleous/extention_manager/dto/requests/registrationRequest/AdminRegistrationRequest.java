package com.oracleous.extention_manager.dto.requests.registrationRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminRegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
