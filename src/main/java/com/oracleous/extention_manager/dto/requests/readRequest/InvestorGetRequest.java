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
@Schema(description = "Request payload required to fetch investor data")
public class InvestorGetRequest {

    @Schema(description = "Email address of the investor", example = "investor@example.com")
    private String email;

    @Schema(description = "Phone number of the investor", example = "08012345678")
    private String phoneNumber;
}