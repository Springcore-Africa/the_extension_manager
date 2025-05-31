package com.oracleous.extention_manager.dto.response.readResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Schema(description = "Response payload for retrieving a full name")
public class FullName {

    @Schema(description = "First name of the individual", example = "Ola")
    private String firstName;

    @Schema(description = "Last name of the individual", example = "Wale")
    private String lastName;
}