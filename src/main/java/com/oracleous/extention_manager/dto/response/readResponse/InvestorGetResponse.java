package com.oracleous.extention_manager.dto.response.readResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvestorGetResponse {

    @Schema(description = "Investor's full name object")
    private FullName fullName;

    @Schema(description = "Investor's email address", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Investor's phone number in international format", example = "+1234567890")
    private String phoneNumber;

    @Schema(description = "Hashed password or placeholder", example = "$2a$10$...")
    private String password;

    @Schema(description = "Brief biography of the investor", example = "Passionate tech investor and startup mentor.")
    private String shortBio;

    @Schema(description = "URL to the investor’s passport photograph", example = "https://example.com/images/john-doe.jpg")
    private String passportPhotograph;
}
