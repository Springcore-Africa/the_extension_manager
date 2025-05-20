package com.oracleous.extention_manager.dto.requests.registrationRequest;

import com.oracleous.extention_manager.data.model.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtensionWorker {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Users users ;
    private String shortBio;
    private String passportPhotograph;

}
