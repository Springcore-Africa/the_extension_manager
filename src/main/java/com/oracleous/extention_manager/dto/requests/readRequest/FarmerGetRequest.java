package com.oracleous.extention_manager.dto.requests.readRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request payload require to fetch Farmer data")
public class FarmerGetRequest {
    private String email ;
    private String phoneNumber ;
}
