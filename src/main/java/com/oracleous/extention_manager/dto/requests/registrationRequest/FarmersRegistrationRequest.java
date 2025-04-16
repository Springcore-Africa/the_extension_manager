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
@Schema(description = "Request payload to register farmer")
public class FarmersRegistrationRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private String nationalId;
    private LocalDateTime dateOfBirth;
    private String stateOfOrigin;
    private String lgaOfOrigin;
    private String residentialAddress;
    private int numberOfChildren;
    private String regNumber;
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Gender gender;
//    private Enum<Gender> gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private MaritalStatus maritalStatus ;
//    private Enum<MaritalStatus> maritalStatus;

    private String ninSlip;
    private String birthCertificate;
    private String lastEducationalCertificate;
    private String passportPhotograph;
}
