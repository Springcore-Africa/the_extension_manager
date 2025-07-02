package com.oracleous.extention_manager.dto.requests.adminViewFarmer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminViewFarmersRequest {
    private String email ;
    private String phoneNumber ;
}
