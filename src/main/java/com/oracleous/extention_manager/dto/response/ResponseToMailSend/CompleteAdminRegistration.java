package com.oracleous.extention_manager.dto.response.ResponseToMailSend;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Response for admin registration completion")
public class CompleteAdminRegistration {
    @Schema(
            description = "Status message indicating the result of the completion request",
            example = "Admin registration completed successfully",
            required = true
    )
    private String message;
}