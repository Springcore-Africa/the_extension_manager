package com.oracleous.extention_manager.services.adminService.completeRegistration;

import com.oracleous.extention_manager.data.model.Admin;
import com.oracleous.extention_manager.data.model.RegistrationToken;
import com.oracleous.extention_manager.data.model.Roles;
import com.oracleous.extention_manager.data.model.Users;
import com.oracleous.extention_manager.data.repositories.AdminRepository;
import com.oracleous.extention_manager.data.repositories.RegistrationTokenRepository;
import com.oracleous.extention_manager.dto.requests.requestEmail.AdminCompletionRequestDto;
import com.oracleous.extention_manager.dto.response.ResponseToMailSend.CompleteAdminRegistration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.oracleous.extention_manager.utilities.ApplicationUtilities.*;

@Service
@AllArgsConstructor
@Slf4j
public class CompleteAdminRegMethod implements CompleteAdminReg{
    private final AdminRepository adminRepository;
    private final RegistrationTokenRepository tokenRepository;
    private BCryptPasswordEncoder passwordEncoder ;

    @Override
    public CompleteAdminRegistration completeAdminRegistration(AdminCompletionRequestDto request) {
        RegistrationToken tokenData = tokenRepository.findByToken(request.getToken()).
            filter(time -> time.getExpiresAt().isAfter(LocalDateTime.now())).
            orElseThrow(()-> new IllegalArgumentException(DURATION_HAS_EXPIRE));
        //I Check if token matches email
        if (!tokenData.getEmail().equals(request.getEmail())) {throw new IllegalArgumentException(TOKEN_DOES_NOT_MATCH);}
            String email = request.getEmail();
        Admin admin = adminRepository.findByUsersEmail(email);

        if (admin == null) {throw new IllegalArgumentException(ADMIN_NOT_FOUND);}
        if (admin.isConfirmed()) {throw new IllegalStateException(ADMIN_ALREADY_CONFIRMED);}
        Users users = Users.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(Roles.ADMIN)
                .build();
        admin.setUsers(users);
        admin.setName(request.getName());
        admin.setConfirmed(true);
        adminRepository.save(admin);
        // Delete token
        tokenRepository.deleteById(request.getToken());
        return CompleteAdminRegistration.builder().message(REGISTRATION_SUCCESSFUL).build();
    }


}
