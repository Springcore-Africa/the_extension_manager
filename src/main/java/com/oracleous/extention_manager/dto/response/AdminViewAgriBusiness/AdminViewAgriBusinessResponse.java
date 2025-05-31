package com.oracleous.extention_manager.dto.response.AdminViewAgriBusiness;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response payload for admin viewing AgriBusiness data")
public class AdminViewAgriBusinessResponse {

    @Schema(description = "Name of the AgriBusiness", example = "GreenFields Ltd")
    private String businessName;

    @Schema(description = "State where the AgriBusiness is located", example = "Lagos")
    private String businessLocationState;
}

