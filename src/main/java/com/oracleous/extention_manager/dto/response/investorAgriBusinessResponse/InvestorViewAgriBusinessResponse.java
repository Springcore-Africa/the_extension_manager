package com.oracleous.extention_manager.dto.response.investorAgriBusinessResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Request payload require to fetch AgriBusiness data")
public class InvestorViewAgriBusinessResponse {
    private String businessName;
    private String businessLocationState;
    private String businessLocationLga ;
    private String businessLocationExact;
    private int farmSize;
    private String agriculturalProduct;
    private int yearlyProduction;
    private int numberOfEmployees;
    private String productDescription;
    private String farmPhotos;
    private String productPhotos;
    private String message;

}

