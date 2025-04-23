package com.oracleous.extention_manager.dto.requests.registrationRequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oracleous.extention_manager.data.model.Gender;
import com.oracleous.extention_manager.data.model.MaritalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "Request payload to register a farmer")
public class FarmersRegistrationRequest {

    @Schema(description = "First name of the farmer", example = "Ola", required = true)
    private String firstName;

    @Schema(description = "Last name of the farmer", example = "Wale", required = true)
    private String lastName;

    @Schema(description = "Farmer's phone number", example = "09109223355", required = true)
    private String phoneNumber;

    @Schema(description = "Farmer's email address", example = "joy@gmail.com", required = true)
    private String email;

    @Schema(description = "Account password", example = "securepassword123", required = true)
    private String password;

    @Schema(description = "National ID card number", example = "WRN290K", required = true)
    private String nationalId;

    @Schema(description = "Date of birth of the farmer", example = "2025-07-20", required = true)
    private LocalDateTime dateOfBirth;

    @Schema(description = "State where the farmer was born", example = "Kano", required = true)
    private String stateOfOrigin;

    @Schema(description = "LGA of the state of origin", example = "Sangisa", required = true)
    private String lgaOfOrigin;

    @Schema(description = "Farmer's residential address", example = "No 6, OPS Estate Sangisa", required = true)
    private String residentialAddress;

    @Schema(description = "Number of children the farmer has", example = "10", required = true)
    private int numberOfChildren;

    @Schema(description = "Farmer's registration number", example = "OFG20", required = true)
    private String regNumber;

    @Schema(description = "Additional description from the farmer", example = "I have registered successfully", required = true)
    private String description;

    @Schema(description = "Gender of the farmer", example = "Male", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Gender gender;

    @Schema(description = "Marital status of the farmer", example = "Single", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private MaritalStatus maritalStatus;

    @Schema(description = "National Identification Number slip URL", example = "https://example.com/nin.jpg", required = true)
    private String ninSlip;

    @Schema(description = "Birth certificate document URL", example = "https://example.com/birth_certificate.pdf", required = true)
    private String birthCertificate;

    @Schema(description = "Last educational certificate URL", example = "https://example.com/education_certificate.pdf", required = true)
    private String lastEducationalCertificate;

    @Schema(description = "Passport photograph URL", example = "https://example.com/passport.jpg", required = true)
    private String passportPhotograph;

    @Schema(description = "Verification token", example = "102930", required = true)
    private String verificationToken;
}
