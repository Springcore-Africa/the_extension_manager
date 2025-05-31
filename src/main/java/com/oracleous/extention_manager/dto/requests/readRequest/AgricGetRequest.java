package com.oracleous.extention_manager.dto.requests.readRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request payload required to fetch AgriBusiness data")
public class AgricGetRequest {

    @Schema(description = "Email address of the AgriBusiness entity", example = "agri.business@example.com")
    private String email;

    @Schema(description = "Phone number of the AgriBusiness entity", example = "09087654321")
    private String phoneNumber;
}