package com.oracleous.extention_manager.dto.requests.registrationRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "Request payload to register agriBusiness")
public class AgriBusinessRegRequest {

    @Schema(description = "businessName", example = "trading", required = true)
    private String businessName;

    @Schema(description = "businessLocationState", example = "abuja", required = true)
    private String businessLocationState;

    @Schema(description = "businessLocationLga", example = "gwagwalada", required = true)
    private String businessLocationLga;

    @Schema(description = "businessLocationExact", example = "aso rock", required = true)
    private String businessLocationExact;

    @Schema(description = "cacNumber", example = "0019OL8", required = true)
    private String cacNumber;

    @Schema(description = "cacRegistrationDate", example = "LocalDate", required = true)
    private LocalDateTime cacRegistrationDate;

    @Schema(description = "farmSize", example = "2", required = true)
    private int farmSize;

    @Schema(description = "agriculturalProduct", example = "rice", required = true)
    private String agriculturalProduct;

    @Schema(description = "yearlyProduction", example = "50", required = true)
    private int yearlyProduction;

    @Schema(description = "numberOfEmployees", example = "20", required = true)
    private int numberOfEmployees;

    @Schema(description = "productDescription", example = "eatable", required = true)
    private String productDescription;

    @Schema(description = "cacCertificate", example = "2o2oij", required = true)
    private String cacCertificate;

    @Schema(description = "memart", example = "jhone", required = true)
    private String memart;

    @Schema(description = "farmPhotos", example = "image", required = true)
    private String farmPhotos;

    @Schema(description = "productPhotos", example = "image", required = true)
    private String productPhotos;

    @Schema(description = "regNumber", example = "1212", required = true)
    private String regNumber;

    @Schema(description = "farmerId", example = "100DRO", required = true)
    private Long farmerId;

}
