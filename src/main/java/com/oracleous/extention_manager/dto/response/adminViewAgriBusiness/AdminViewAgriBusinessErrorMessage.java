package com.oracleous.extention_manager.dto.response.adminViewAgriBusiness;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response payload for error messages related to admin viewing AgriBusiness data")
public class AdminViewAgriBusinessErrorMessage {

    @Schema(description = "Error message describing the issue encountered", example = "AgriBusiness data not found")
    private String message;
}