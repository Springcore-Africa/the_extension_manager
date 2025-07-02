package com.oracleous.extention_manager.dto.response.investorAgriBusinessResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Response payload for investor viewing AgriBusiness data")
public class InvestorViewAgriBusinessResponse {

    @Schema(description = "Name of the AgriBusiness", example = "GreenFields Ltd")
    private String businessName;

    @Schema(description = "State where the AgriBusiness is located", example = "Lagos")
    private String businessLocationState;

    @Schema(description = "Local Government Area (LGA) where the AgriBusiness is located", example = "Ikeja")
    private String businessLocationLga;

    @Schema(description = "Exact address or location of the AgriBusiness", example = "12 Farm Road, Ikeja")
    private String businessLocationExact;

    @Schema(description = "Size of the farm in hectares", example = "50")
    private int farmSize;

    @Schema(description = "Primary agricultural product of the AgriBusiness", example = "Maize")
    private String agriculturalProduct;

    @Schema(description = "Yearly production output in metric tons", example = "1000")
    private int yearlyProduction;

    @Schema(description = "Number of employees in the AgriBusiness", example = "25")
    private int numberOfEmployees;

    @Schema(description = "Description of the AgriBusiness product", example = "High-quality maize for local and export markets")
    private String productDescription;

    @Schema(description = "URL to photos of the farm", example = "https://example.com/farm_photos.jpg")
    private String farmPhotos;

    @Schema(description = "URL to photos of the agricultural product", example = "https://example.com/product_photos.jpg")
    private String productPhotos;

    @Schema(description = "Message describing the result of the operation", example = "AgriBusiness data retrieved successfully")
    private String message;
}