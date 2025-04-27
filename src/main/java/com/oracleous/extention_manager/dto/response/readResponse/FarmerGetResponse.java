package com.oracleous.extention_manager.dto.response.readResponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oracleous.extention_manager.data.model.Gender;
import com.oracleous.extention_manager.data.model.MaritalStatus;
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
public class FarmerGetResponse {
    private FullName fullName;
    private String email;
    private String phoneNumber;
    private String password;
    private String nationalId;
    private LocalDate dateOfBirth;
    private String stateOfOrigin;
    private String lgaOfOrigin;
    private String residentialAddress;
    private int numberOfChildren;
    private String regNumber;
    private String description;
//    private String business

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Gender gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private MaritalStatus maritalStatus ;

    private String ninSlip;
    private String birthCertificate;
    private String lastEducationalCertificate;
    private String passportPhotograph;
}
