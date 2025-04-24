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
@Schema(description = "Response for Farmer Registration")
public class FarmerResponse {

    @Schema(description = "Response code indicating the result of the request", example = "200")
    private String responseCode;

    @Schema(description = "Message describing the registration outcome", example = "Farmer registered successfully")
    private String responseMessage;

    @Schema(description = "Contains detailed information about the registered farmer")
    private FarmerInfo farmerInfo;
}
