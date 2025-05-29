package com.oracleous.extention_manager.services.superAdminServices.superAdminRegistration;

import com.oracleous.extention_manager.data.model.Roles;
import com.oracleous.extention_manager.data.model.SuperAdmin;
import com.oracleous.extention_manager.data.model.Users;
import com.oracleous.extention_manager.data.repositories.SuperAdminRepository;
import com.oracleous.extention_manager.dto.requests.registrationRequest.SuperAdminRegRequest;
import com.oracleous.extention_manager.dto.response.registrationResponse.SuperAdminResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.oracleous.extention_manager.utilities.ApplicationUtilities.ACCOUNT_CREATED_MESSAGE;
import static com.oracleous.extention_manager.utilities.ApplicationUtilities.SUPER_ADMIN_EXIST;

@Service
@AllArgsConstructor
@Slf4j
public class SuperAdminRegistrationImplementation implements SuperAdminRegistration {
    private final SuperAdminRepository superAdminRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public SuperAdminResponse superAdminRegistration(SuperAdminRegRequest superAdminRegRequest) {

        boolean superAdminExist = superAdminRepository.existsByUsers_Email(superAdminRegRequest.getEmail());
        if (superAdminExist) {
            return SuperAdminResponse.builder()
                .message(SUPER_ADMIN_EXIST)
                .build();
        }
        Users users = Users.builder()
            .email(superAdminRegRequest.getEmail())
            .password(passwordEncoder.encode(superAdminRegRequest.getPassword()))
            .userRole(Roles.SUPER_ADMIN)
            .build();
        SuperAdmin superAdminReg = SuperAdmin.builder()
            .users(users)
            .firstName(superAdminRegRequest.getFirstName())
            .lastName(superAdminRegRequest.getLastName())
            .phoneNumber(superAdminRegRequest.getPhoneNumber())
            .passportPhotograph(superAdminRegRequest.getPassportPhotograph())
            .build();
        superAdminRepository.save(superAdminReg);
        return SuperAdminResponse.builder()
            .message(ACCOUNT_CREATED_MESSAGE)
            .build();
    }
}