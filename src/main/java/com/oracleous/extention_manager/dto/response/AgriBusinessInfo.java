package com.oracleous.extention_manager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgriBusinessInfo {
    private String businessName;
    private String businessRgeNumber;
    private String farmersName;
}
