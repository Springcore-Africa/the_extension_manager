package com.oracleous.extention_manager.services.localAdminService.localAdminRegistration;

import com.oracleous.extention_manager.data.model.LocalAdmin;
import com.oracleous.extention_manager.data.model.Roles;
import com.oracleous.extention_manager.data.model.Users;
import com.oracleous.extention_manager.data.repositories.LocalAdminRepository;
import com.oracleous.extention_manager.dto.requests.registrationRequest.LocalAdminRegRequest;
import com.oracleous.extention_manager.dto.response.registrationResponse.LocalAdminRegResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.oracleous.extention_manager.utilities.ApplicationUtilities.INVESTOR_CREATED_MESSAGE;

@Service
@AllArgsConstructor
public class LocalAdminServiceRegistrationMethod implements LocalAdminService {
    private final LocalAdminRepository localAdminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public LocalAdminRegResponse localAdminRegistration(LocalAdminRegRequest localAdminRegRequest) {
        boolean localAdminExists = localAdminRepository.existsByUsersEmailAndPhoneNumber(
                localAdminRegRequest.getEmail() ,
                localAdminRegRequest.getPhoneNumber()
        );

        if(localAdminExists) {
            throw new IllegalArgumentException("Email already exists");
        }

        Users users = Users.builder()
                .email(localAdminRegRequest.getEmail())
                .password(passwordEncoder.encode(localAdminRegRequest.getPassword()))
                .userRole(Roles.LOCAL_ADMIN)
                .build();

        LocalAdmin localAdmin = LocalAdmin.builder()
                .users(users)
                .lastName(localAdminRegRequest.getLastName())
                .firstName(localAdminRegRequest.getFirstName())
                .passportPhotograph(localAdminRegRequest.getPassportPhotograph())
                .shortBio(localAdminRegRequest.getShortBio())
                .build();

        LocalAdmin savedLocalAdmin = localAdminRepository.save(localAdmin);

        return LocalAdminRegResponse.builder()
                .firstName(savedLocalAdmin.getFirstName())
                .lastName(savedLocalAdmin.getLastName())
                .responseMessage(INVESTOR_CREATED_MESSAGE)
                .build();

    }
}
