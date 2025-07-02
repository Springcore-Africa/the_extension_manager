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
public class FarmerInfo {

    @Schema(description = "Full name of the farmer", example = "Aisha Bello")
    private String farmersName;

    @Schema(description = "Unique registration number assigned to the farmer", example = "FRM-2025-00123")
    private String farmersRegNumber;
}
