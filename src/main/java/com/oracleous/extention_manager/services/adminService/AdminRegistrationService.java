package com.oracleous.extention_manager.services.adminService;

import com.oracleous.extention_manager.data.model.Admin;
import com.oracleous.extention_manager.data.model.SuperAdmin;
import com.oracleous.extention_manager.data.repositories.AdminRepository;
import com.oracleous.extention_manager.data.repositories.SuperAdminRepository;
import com.oracleous.extention_manager.dto.requests.requestEmail.AdminRegistrationRequestDto;
import com.oracleous.extention_manager.dto.requests.requestEmail.AdminCompletionRequestDto;
import com.oracleous.extention_manager.dto.response.ResponseToMailSend.InitiateAdminRegistration;
import com.oracleous.extention_manager.email.EmailEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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
            throw new SecurityException("Only SuperAdmin can initiate registration");
        }
        log.info("Using base URL: {}", baseUrl);

        // Validate email
        if (request.getEmail() == null || !request.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Invalid email address");
        }

        if (adminRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        //I Created Admin Here
        Admin admin = Admin.builder()
                .email(request.getEmail())
                .confirmed(false)
                .build();
        adminRepository.save(admin);

        //I Generate and send email
        String encodedEmail = URLEncoder.encode(request.getEmail(), StandardCharsets.UTF_8);
        String link = baseUrl + "/admin/register/complete?email=" + encodedEmail;
        log.info("Generated registration link: {}", link);

        String subject = "Complete Your Admin Registration";
        String text = "<p>Click to complete your registration:</p>" +
                "<a href=\"" + link + "\">Complete Registration</a>";

        eventPublisher.publishEvent(new EmailEvent(this, request.getEmail(), subject, text));

        return InitiateAdminRegistration.builder()
                .message("Registration email sent")
                .build();
    }

    @Override
    public void completeAdminRegistration(String email, AdminCompletionRequestDto request) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin == null) {
            throw new IllegalArgumentException("Admin not found");
        }
        if (admin.isConfirmed()) {
            throw new IllegalStateException("Admin already confirmed");
        }

        //I Update Admin Here
        admin.setName(request.getName());
        admin.setPassword(request.getPassword());
        admin.setConfirmed(true);
        adminRepository.save(admin);
    }
}