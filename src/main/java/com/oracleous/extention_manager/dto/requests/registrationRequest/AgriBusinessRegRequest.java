package com.oracleous.extention_manager.dto.requests.registrationRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "Request payload to register an AgriBusiness")
public class AgriBusinessRegRequest {

    @Schema(description = "Name of the business", example = "Green Harvest Ltd.", required = true)
    private String businessName;

    @Schema(description = "State where the business is located", example = "Abuja", required = true)
    private String businessLocationState;

    @Schema(description = "LGA where the business is located", example = "Gwagwalada", required = true)
    private String businessLocationLga;

    @Schema(description = "Exact business location", example = "Aso Rock", required = true)
    private String businessLocationExact;

    @Schema(description = "CAC registration number of the business", example = "0019OL8", required = true)
    private String cacNumber;

    @Schema(description = "Size of the farm in hectares", example = "2", required = true)
    private int farmSize;

    @Schema(description = "Main agricultural product grown", example = "Rice", required = true)
    private String agriculturalProduct;

    @Schema(description = "Yearly production capacity (in tons)", example = "50", required = true)
    private int yearlyProduction;

    @Schema(description = "Number of employees working in the business", example = "20", required = true)
    private int numberOfEmployees;

    @Schema(description = "Brief description of the product", example = "Edible grain", required = true)
    private String productDescription;

    @Schema(description = "CAC certificate document URL", example = "https://example.com/certificate.pdf", required = true)
    private String cacCertificate;

    @Schema(description = "Memorandum and Articles of Association (MEMART)", example = "John Doe", required = true)
    private String memart;

    @Schema(description = "URL for farm photos", example = "https://example.com/farm.jpg", required = true)
    private String farmPhotos;

    @Schema(description = "URL for product photos", example = "https://example.com/product.jpg", required = true)
    private String productPhotos;

    @Schema(description = "AgriBusiness registration number", example = "1212", required = true)
    private String regNumber;

//    @Schema(description = "Farmer ID associated with the business", example = "100DRO", required = true)
//    private Long farmerId;
}
