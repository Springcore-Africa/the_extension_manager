package com.oracleous.extention_manager.dto.requests.registrationRequest;

import com.oracleous.extention_manager.data.model.Roles;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request payload for SuperAdmin registration")
public class SuperAdminRegRequest {
    @Schema(description = "First name of the SuperAdmin", example = "John", required = true)
    private String firstName;

    @Schema(description = "Last name of the SuperAdmin", example = "Doe", required = true)
    private String lastName;

    @Schema(description = "Phone number of the SuperAdmin", example = "+1234567890012", required = true)
    private String phoneNumber;

    @Schema(description = "Email address of the SuperAdmin", example = "john.doe@example.com", required = true)
    private String email;

    @Schema(description = "Password for the SuperAdmin account", example = "SecurePass123!", required = true)
    private String password;

    private Roles role;

    @Schema(description = "Short biography of the SuperAdmin", example = "Experienced admin with at least a year in management", required = false)
    private String shortBio;

    @Schema(description = "URL or path to the passport photograph", example = "/images/passport.jpg", required = false)
    private String passportPhotograph;


}
