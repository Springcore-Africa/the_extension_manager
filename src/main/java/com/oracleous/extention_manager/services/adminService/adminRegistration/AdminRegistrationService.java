package com.oracleous.extention_manager.services.adminService.adminRegistration;

import com.oracleous.extention_manager.data.model.Admin;
import com.oracleous.extention_manager.data.model.RegistrationToken;
import com.oracleous.extention_manager.data.model.Users;
import com.oracleous.extention_manager.data.repositories.AdminRepository;
import com.oracleous.extention_manager.data.repositories.RegistrationTokenRepository;
import com.oracleous.extention_manager.data.repositories.SuperAdminRepository;
import com.oracleous.extention_manager.dto.requests.requestEmail.AdminRegistrationRequestDto;
import com.oracleous.extention_manager.dto.response.ResponseToMailSend.InitiateAdminRegistration;
import com.oracleous.extention_manager.email.EmailEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.oracleous.extention_manager.utilities.ApplicationUtilities.*;

@Service
//@AllArgsConstructor
@Slf4j
public class AdminRegistrationService implements AdminRegistration {
    private final AdminRepository adminRepository;
    private final SuperAdminRepository superAdminRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final RegistrationTokenRepository tokenRepository;

    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;

    public AdminRegistrationService(AdminRepository adminRepository,
                                    SuperAdminRepository superAdminRepository,
                                    RegistrationTokenRepository tokenRepository,
                                    ApplicationEventPublisher eventPublisher) {
        this.adminRepository = adminRepository;
        this.superAdminRepository = superAdminRepository;
        this.eventPublisher = eventPublisher;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public InitiateAdminRegistration initiateAdminRegistration(AdminRegistrationRequestDto request, String superAdminEmail) {
//        if (!superAdminRepository .existsByEmail(superAdminEmail)) {
//                throw new SecurityException(SUPER_ADMIN_INITIATIVE);
//        }
        log.info("Using base URL: {}", baseUrl);

        //I validate email
        if (request.getEmail() == null || !request.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException(INVALID_EMAIL_ADDRESS);
        }

        if (adminRepository.existsByUsersEmail(request.getEmail())) {
            throw new IllegalArgumentException(EMAIL_ALREADY_EXIST);
        }

        Users users = Users.builder()
                .email(request.getEmail())
                .userRole(request.getRole())
                .build();

        Admin admin = Admin.builder()
                .users(users)
                .confirmed(false)
                .build();
        adminRepository.save(admin);

        // I generate and save token
        String token = UUID.randomUUID().toString();
        RegistrationToken registrationToken = new RegistrationToken();
        registrationToken.setToken(token);
        registrationToken.setEmail(request.getEmail());
        registrationToken.setCreatedAt(LocalDateTime.now());
        registrationToken.setExpiresAt(LocalDateTime.now().plusHours(2));
        tokenRepository.save(registrationToken);

        //I generate and send email here
        String link = baseUrl + "/admin/register/complete-form?token=" + token;
        log.info("Generated registration link: {}", link);

        String subject = "Complete Your Admin Registration";
        String text = "<p>Click to complete your registration within two hours:</p>" +
                "<a href=\"" + link + "\">Complete Registration</a>" +
                "<p> This link will expire after 2 hours.</p>";  // i added this
        eventPublisher.publishEvent(new EmailEvent(this, request.getEmail(), subject, text));

        return InitiateAdminRegistration.builder()
                .message(EMAIL_REGISTRATION_SENT)
                .build();
    }

    @Override
    public boolean isAdminEmailRegistered(String email) {
        return adminRepository.existsByUsersEmail(email);
    }

    public RegistrationToken validateToken(String token) {
        return tokenRepository.findByToken(token)
                .filter(t -> t.getExpiresAt().isAfter(LocalDateTime.now()))
                .orElse(null);
    }
}