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
@Schema(description = "Response for Admin Registration ")
public class AdminRegistrationResponse {
    private String message ;
}
