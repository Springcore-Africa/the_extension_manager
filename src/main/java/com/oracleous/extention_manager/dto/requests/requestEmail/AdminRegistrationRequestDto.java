package com.oracleous.extention_manager.dto.requests.requestEmail;

import com.oracleous.extention_manager.data.model.Roles;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request body for initiating admin registration")
public class AdminRegistrationRequestDto {
    @NotNull(message = "Email is required")
    @Email(message = "Invalid email address")
    @Schema(
            description = "Admin email address, must match the regex ^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
            example = "admin@example.com",
            required = true,
            pattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"
    )
    private String email;
//    private Roles role;
}