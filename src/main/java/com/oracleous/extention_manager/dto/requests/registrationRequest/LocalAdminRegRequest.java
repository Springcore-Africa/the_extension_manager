package com.oracleous.extention_manager.dto.requests.registrationRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request payload to register a local administrator")
public class LocalAdminRegRequest {

    @Schema(description = "First name of the local administrator", example = "Ada")
    private String firstName;

    @Schema(description = "Last name of the local administrator", example = "Okeke")
    private String lastName;

    @Schema(description = "Phone number of the local administrator", example = "08012345678")
    private String phoneNumber;

    @Schema(description = "Email address of the local administrator", example = "ada.okeke@example.com")
    private String email;

    @Schema(description = "Account password for the local administrator", example = "securePass789")
    private String password;

    @Schema(description = "Brief biography or description of the local administrator", example = "Experienced administrator with 5 years in community management")
    private String shortBio;

    @Schema(description = "URL to the passport photograph of the local administrator", example = "https://example.com/passport.jpg")
    private String passportPhotograph;
}