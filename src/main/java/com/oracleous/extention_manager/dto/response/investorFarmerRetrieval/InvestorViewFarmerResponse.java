package com.oracleous.extention_manager.dto.response.investorFarmerRetrieval;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Response payload for investor viewing farmer data")
public class InvestorViewFarmerResponse {

    @Schema(description = "First name of the farmer", example = "Ola")
    private String firstName;

    @Schema(description = "Last name of the farmer", example = "Wale")
    private String lastName;

    @Schema(description = "Residential address of the farmer", example = "No 6, OPS Estate, Sangisa")
    private String residentialAddress;

    @Schema(description = "URL to the farmer's picture or passport photograph", example = "https://example.com/farmer_photo.jpg")
    private String picture;

    @Schema(description = "Short description or bio of the farmer", example = "Experienced maize farmer with 10 years in agriculture")
    private String shortDescription;

    @Schema(description = "Marital status of the farmer", example = "Married")
    private String maritalStatus;
}