package com.oracleous.extention_manager.dto.requests.farmPackage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmRequest {
    private String farmName;
    private String location;
    private String farmSize;
    private String cropPlanted;
    private MultipartFile farmPicture;

}
