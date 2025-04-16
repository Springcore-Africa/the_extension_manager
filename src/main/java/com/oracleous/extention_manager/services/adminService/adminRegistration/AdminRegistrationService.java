package com.oracleous.extention_manager.services.adminService.adminRegistration;

import com.oracleous.extention_manager.data.model.Admin;
import com.oracleous.extention_manager.data.repositories.AdminRepository;
import com.oracleous.extention_manager.data.repositories.SuperAdminRepository;
import com.oracleous.extention_manager.dto.requests.requestEmail.AdminRegistrationRequestDto;
import com.oracleous.extention_manager.dto.response.ResponseToMailSend.InitiateAdminRegistration;
import com.oracleous.extention_manager.email.EmailEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.oracleous.extention_manager.utilities.ApplicationUtilities.*;

@Service
@Slf4j
public class AdminRegistrationService implements AdminRegistration {
    private final AdminRepository adminRepository;
    private final SuperAdminRepository superAdminRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;

    public AdminRegistrationService(AdminRepository adminRepository,
                                    SuperAdminRepository superAdminRepository,
                                    ApplicationEventPublisher eventPublisher) {
        this.adminRepository = adminRepository;
        this.superAdminRepository = superAdminRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public InitiateAdminRegistration initiateAdminRegistration(AdminRegistrationRequestDto request, String superAdminEmail) {
        if (!superAdminRepository.existsByEmail(superAdminEmail)) {
                throw new SecurityException(SUPER_ADMIN_INITIATIVE);
        }
        log.info("Using base URL: {}", baseUrl);

        //I validate email
        if (request.getEmail() == null || !request.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException(INVALID_EMAIL_ADDRESS);
        }

        if (adminRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(EMAIL_ALREADY_EXIST);
        }
        Admin admin = Admin.builder()
                .email(request.getEmail())
                .confirmed(false)
                .build();
        adminRepository.save(admin);

        //I generate and send email here
        String encodedEmail = URLEncoder.encode(request.getEmail(), StandardCharsets.UTF_8);
        String link = baseUrl + "/admin/register/complete-form?email=" + encodedEmail; //I Changed to new endpoint here
        log.info("Generated registration link: {}", link);

        String subject = "Complete Your Admin Registration";
        String text = "<p>Click to complete your registration:</p>" +
                "<a href=\"" + link + "\">Complete Registration</a>";
        eventPublisher.publishEvent(new EmailEvent(this, request.getEmail(), subject, text));

        return InitiateAdminRegistration.builder()
                .message(EMAIL_REGISTRATION_SENT)
                .build();
    }

//    @Override
//    public CompleteAdminRegistration completeAdminRegistration(AdminCompletionRequestDto request) {
//        String email = request.getEmail();
//        Admin admin = adminRepository.findByEmail(email);
//        if (admin == null) {
//            throw new IllegalArgumentException(ADMIN_NOT_FOUND);
//        }
//        if (admin.isConfirmed()) {
//            throw new IllegalStateException(ADMIN_ALREADY_CONFIRMED);
//        }
//
//        admin.setEmail(request.getEmail());
//        admin.setName(request.getName());
//        admin.setPassword(request.getPassword());
//        admin.setConfirmed(true);
//        adminRepository.save(admin);
//        return CompleteAdminRegistration.builder().
//                message("Registration successful")
//                .build();
//    }

    @Override
    public boolean isAdminEmailRegistered(String email) {
        return adminRepository.existsByEmail(email);
    }
}