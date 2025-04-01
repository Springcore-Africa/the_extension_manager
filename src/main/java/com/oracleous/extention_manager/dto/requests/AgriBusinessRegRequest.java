package com.oracleous.extention_manager.dto.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AgriBusinessRegRequest {
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

}
