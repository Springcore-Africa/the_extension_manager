package com.oracleous.extention_manager.dto.requests.readRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request payload to fetch farmer details using email or phone number")
public class FarmerGetRequest {

    @Schema(description = "Farmer's email address", example = "farmer@example.com", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Email(message = "Email must be valid")
    private String email;

    @Schema(description = "Farmer's phone number", example = "+2341234567890", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone number must be valid")
    private String phoneNumber;
}