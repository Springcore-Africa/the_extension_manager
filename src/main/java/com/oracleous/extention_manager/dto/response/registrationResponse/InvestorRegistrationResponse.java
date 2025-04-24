package com.oracleous.extention_manager.dto.response.registrationResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Response for Investor Registration")
public class InvestorRegistrationResponse {

    @Schema(description = "Message indicating the registration outcome", example = "Investor registered successfully")
    private String responseMessage;

    @Schema(description = "First name of the registered investor", example = "John")
    private String FirstName;

    @Schema(description = "Last name of the registered investor", example = "Doe")
    private String LastName;
}
