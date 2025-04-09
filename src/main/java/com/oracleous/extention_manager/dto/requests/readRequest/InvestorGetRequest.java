package com.oracleous.extention_manager.dto.requests.readRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvestorGetRequest {
    private String email ;
    private String phoneNumber ;

}
