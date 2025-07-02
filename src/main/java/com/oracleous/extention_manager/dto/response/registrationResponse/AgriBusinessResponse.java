package com.oracleous.extention_manager.dto.response.registrationResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Response for AgriBusiness registration process")
public class AgriBusinessResponse {

    @Schema(description = "Response code indicating the result of the request", example = "200")
    private String responseCode;

    @Schema(description = "Message providing details about the response outcome", example = "AgriBusiness registered successfully")
    private String responseMessage;

    @Schema(description = "Contains detailed information about the registered AgriBusiness")
    private AgriBusinessInfo agriBusinessInfo;
}
