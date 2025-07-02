package com.oracleous.extention_manager.dto.response.ResponseToMailSend;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response for admin registration initiation")
public class InitiateAdminRegistration {
    @Schema(
            description = "Status message indicating the result of the initiation request",
            example = "Registration email sent successfully",
            required = true
    )
    private String message;
}