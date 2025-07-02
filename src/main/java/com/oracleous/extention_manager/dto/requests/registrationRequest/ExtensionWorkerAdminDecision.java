package com.oracleous.extention_manager.dto.requests.registrationRequest;

import com.oracleous.extention_manager.data.model.Stamp;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request payload for an admin decision regarding an extension worker")
public class ExtensionWorkerAdminDecision {

    @Schema(description = "Email address of the extension worker", example = "worker@example.com")
    private String email;

    @Schema(description = "Action taken by the admin (e.g., APPROVE, REJECT)", example = "APPROVE")
    private Stamp action;
}