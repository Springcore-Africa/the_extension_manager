package com.oracleous.extention_manager.dto.requests;

import com.oracleous.extention_manager.data.model.Gender;
import com.oracleous.extention_manager.data.model.MaritalStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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
    private Enum<Gender> gender;
    private Enum<MaritalStatus> maritalStatus;
    private String ninSlip;
    private String birthCertificate;
    private String lastEducationalCertificate;
    private String passportPhotograph;
}
