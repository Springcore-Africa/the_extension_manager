package com.oracleous.extention_manager.dto.requests.loginRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Request payload for user login")
public class LoginRequest {

    @Schema(description = "Email address of the user", example = "user@example.com")
    private String email;

    @Schema(description = "Password of the user", example = "securePass123")
    private String password;
}