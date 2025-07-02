package com.oracleous.extention_manager.dto.requests.registrationRequest;

import com.oracleous.extention_manager.data.model.Roles;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationRequest {

    @Schema(description = "User email", example = "joy@gmail.com", required = true)
    private String email;

    @Schema(description = "User password", example = "securePassword123", required = true)
    private String password;

    @Schema(description = "Role of the user", example = "FARMER", required = true)
    private Roles userRole;
}
