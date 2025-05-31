package com.oracleous.extention_manager.dto.response.readResponse;

import com.oracleous.extention_manager.data.model.Gender;
import com.oracleous.extention_manager.data.model.MaritalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response payload for retrieving AgriBusiness data")
public class AgricGetResponse {

    @Schema(description = "Name of the AgriBusiness", example = "GreenFields Ltd")
    private String businessName;

    @Schema(description = "State where the AgriBusiness is located", example = "Lagos")
    private String businessLocationState;

    @Schema(description = "Local Government Area (LGA) where the AgriBusiness is located", example = "Ikeja")
    private String businessLocationLga;

    @Schema(description = "Exact address or location of the AgriBusiness", example = "12 Farm Road, Ikeja")
    private String businessLocationExact;

    @Schema(description = "Corporate Affairs Commission (CAC) registration number", example = "CAC123456")
    private String cacNumber;

    @Schema(description = "Date of CAC registration", example = "2023-05-15T10:30:00")
    private LocalDateTime cacRegistrationDate;

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

    @Schema(description = "URL to the CAC certificate document", example = "https://example.com/cac_certificate.pdf")
    private String cacCertificate;

    @Schema(description = "URL to the Memorandum and Articles of Association (Memart) document", example = "https://example.com/memart.pdf")
    private String memart;

    @Schema(description = "URL to photos of the farm", example = "https://example.com/farm_photos.jpg")
    private String farmPhotos;

    @Schema(description = "URL to photos of the agricultural product", example = "https://example.com/product_photos.jpg")
    private String productPhotos;

    @Schema(description = "Registration number of the AgriBusiness", example = "AGB789")
    private String regNumber;

    @Schema(description = "ID of the associated farmer", example = "12345")
    private Long farmerId;

    @Schema(description = "Gender of the associated farmer", example = "MALE")
    private Gender gender;

    @Schema(description = "Response message describing the result of the operation", example = "AgriBusiness data retrieved successfully")
    private String responseMessage;
}