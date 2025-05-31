package com.oracleous.extention_manager.dto.response.loginResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Response payload for user login")
public class LoginResponse {

    @Schema(description = "Authentication token for the user session", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String token;

    @Schema(description = "Message describing the result of the login operation", example = "Login successful")
    private String message;
}

