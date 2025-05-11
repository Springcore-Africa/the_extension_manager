package com.oracleous.extention_manager.dto.response.registrationResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema
public class LocalAdminRegResponse {

    @Schema(description = "First name of the registered investor", example = "John")
    private String firstName;

    @Schema(description = "Last name of the registered investor", example = "Doe")
    private String lastName;

    @Schema(description = "Message providing details about the response outcome", example = "AgriBusiness registered successfully")
    private String responseMessage;
}
