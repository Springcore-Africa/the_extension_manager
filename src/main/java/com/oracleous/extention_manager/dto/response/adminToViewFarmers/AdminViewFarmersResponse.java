package com.oracleous.extention_manager.dto.response.adminToViewFarmers;

import com.oracleous.extention_manager.data.model.AgriBusiness;
import com.oracleous.extention_manager.data.model.Gender;
import com.oracleous.extention_manager.data.model.MaritalStatus;
import com.oracleous.extention_manager.data.model.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminViewFarmersResponse {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Users users ;
    private String nationalId;
    private LocalDate dateOfBirth;
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
    private AgriBusiness agriBusiness;
}
