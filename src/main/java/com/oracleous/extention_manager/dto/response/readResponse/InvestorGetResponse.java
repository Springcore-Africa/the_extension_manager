package com.oracleous.extention_manager.dto.response.readResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvestorGetResponse {
    private FullName fullName;
    private String email;
    private String phoneNumber;
    private String password;
    private String shortBio;
    private String passportPhotograph;
}
