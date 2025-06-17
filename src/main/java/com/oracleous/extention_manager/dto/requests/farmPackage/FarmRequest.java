package com.oracleous.extention_manager.dto.requests.farmPackage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmRequest {
    private String farmName;
    private String location;
    private String farmSize;
    private String cropPlanted;
    private byte[] farmPicture;

}
