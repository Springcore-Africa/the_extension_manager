package com.oracleous.extention_manager.dto.response.readResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FullName {
    private String firstName;
    private String lastName;
}
