package com.oracleous.extention_manager.dto.response.readResponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oracleous.extention_manager.data.model.Gender;
import com.oracleous.extention_manager.data.model.MaritalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response payload containing detailed farmer information")
public class FarmerGetResponse {

    @Schema(description = "Farmer's full name", requiredMode = Schema.RequiredMode.REQUIRED)
    private FullName fullName;

    @Schema(description = "Farmer's email address", example = "farmer@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "Farmer's phone number", example = "+2341234567890", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phoneNumber;

//    @Schema(description = "Farmer's password (hashed)", example = "********", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
//    private String password;

    @Schema(description = "Farmer's national ID", example = "12345678901", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nationalId;

    @Schema(description = "Farmer's date of birth", example = "1985-06-15", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate dateOfBirth;

    @Schema(description = "Farmer's state of origin", example = "Lagos", requiredMode = Schema.RequiredMode.REQUIRED)
    private String stateOfOrigin;

    @Schema(description = "Farmer's local government area of origin", example = "Ikeja", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lgaOfOrigin;

    @Schema(description = "Farmer's residential address", example = "123 Farm Road, Lagos", requiredMode = Schema.RequiredMode.REQUIRED)
    private String residentialAddress;

    @Schema(description = "Number of children", example = "2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private int numberOfChildren;

    @Schema(description = "Farmer's registration number", example = "FARM123456", requiredMode = Schema.RequiredMode.REQUIRED)
    private String regNumber;

    @Schema(description = "Description of farmer's activities", example = "Crop farming", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    @Schema(description = "Farmer's gender", example = "MALE", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Gender gender;

    @Schema(description = "Farmer's marital status", example = "MARRIED", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private MaritalStatus maritalStatus;

    @Schema(description = "Path to NIN slip document", example = "/documents/nin-slip.pdf", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String ninSlip;

    @Schema(description = "Path to birth certificate document", example = "/documents/birth-certificate.pdf", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String birthCertificate;

    @Schema(description = "Path to last educational certificate", example = "/documents/degree.pdf", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String lastEducationalCertificate;

    @Schema(description = "Path to passport photograph", example = "/documents/passport.jpg", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String passportPhotograph;
}