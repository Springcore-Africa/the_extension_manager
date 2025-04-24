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
@Schema(description = "Response for SuperAdmin registration")
public class SuperAdminResponse {
    @Schema(description = "Message indicating the result of the registration", example = "Account created successfully")
    private String message ;
}
