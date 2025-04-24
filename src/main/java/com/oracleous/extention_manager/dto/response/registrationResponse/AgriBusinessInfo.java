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
@Schema(description = "Represents AgriBusiness registration details")
public class AgriBusinessInfo {

    @Schema(description = "Name of the agribusiness", example = "Green Harvest Ltd.")
    private String businessName;

    @Schema(description = "Registration number of the agribusiness", example = "AGB12345XYZ")
    private String businessRgeNumber;

    @Schema(description = "Name of the farmer associated with the business", example = "John Doe")
    private String farmersName;
}
