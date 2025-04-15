package com.oracleous.extention_manager.dto.response.registrationResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmerInfo {
    private String farmersName;
    private String farmersRegNumber;
}
