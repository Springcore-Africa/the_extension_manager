package com.oracleous.extention_manager.dto.requests.requestEmail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request body for completing admin registration")
public class AdminCompletionRequestDto {
    @Schema(
            description = "UUID-based registration token sent via email",
            example = "123456",
            required = true
    )
    private String token;

    @Schema(
            description = "Admin email address",
            example = "admin@example.com",
            required = true
    )
    private String email;

    @Schema(
            description = "Admin's full name",
            example = "John Doe",
            required = true
    )
    private String name;

    @Schema(
            description = "Admin's password",
            example = "securePassword123",
            required = true
    )
    private String password;
}