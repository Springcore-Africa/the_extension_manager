package com.oracleous.extention_manager.dto.response.extensionWorkerResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response payload for extension worker operations")
public class ExtensionWorkerResponse {

    @Schema(description = "Message describing the result of the extension worker operation", example = "Extension worker registered successfully")
    private String message;
}