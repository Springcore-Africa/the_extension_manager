package com.oracleous.extention_manager.dto.response.readResponse;

import com.oracleous.extention_manager.data.model.Gender;
import com.oracleous.extention_manager.data.model.MaritalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgricGetResponse {
    private String businessName;
    private String businessLocationState;
    private String businessLocationLga;
    private String businessLocationExact;
    private String cacNumber;
    private LocalDateTime cacRegistrationDate;
    private int farmSize;
    private String agriculturalProduct;
    private int yearlyProduction;
    private int numberOfEmployees;
    private String productDescription;
    private String cacCertificate;
    private String memart;
    private String farmPhotos;
    private String productPhotos;
    private String regNumber;
    private Long farmerId;
    private Gender gender;
//    private MaritalStatus maritalStatus;

    // This is error response
//    private String responseCode;
    private String responseMessage;
}
