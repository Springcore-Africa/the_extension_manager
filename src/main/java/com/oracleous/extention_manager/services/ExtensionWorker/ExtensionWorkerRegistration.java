package com.oracleous.extention_manager.services.ExtensionWorker;

import com.oracleous.extention_manager.data.model.*;
import com.oracleous.extention_manager.data.repositories.ExtensionWorkerRepository;
import com.oracleous.extention_manager.data.repositories.RegistrationTokenRepository;
import com.oracleous.extention_manager.dto.requests.registrationRequest.ExtensionWorkerAdminDecision;
import com.oracleous.extention_manager.dto.requests.registrationRequest.ExtensionWorkerRequest;
import com.oracleous.extention_manager.dto.requests.registrationRequest.TokenVerificationRequest;
import com.oracleous.extention_manager.dto.response.extensionWorkerResponse.ExtensionWorkerResponse;
import com.oracleous.extention_manager.email.EmailEvent;
import com.oracleous.extention_manager.utilities.ApplicationUtilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static com.oracleous.extention_manager.utilities.ApplicationUtilities.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExtensionWorkerRegistration implements ExtentionWorker {
    private final ExtensionWorkerRepository extensionWorkerRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ConcurrentHashMap<String, ExtensionWorker> pendingRegistrations = new ConcurrentHashMap<>();
    private final RegistrationTokenRepository tokenRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;

    @Override
    public ExtensionWorkerResponse extensionWorker(ExtensionWorkerRequest extensionWorkerRequest) {
        boolean exists = extensionWorkerRepository.existsByUsersEmailAndPhoneNumber(
                extensionWorkerRequest.getEmail(),
                extensionWorkerRequest.getPhoneNumber()
        );
        if (exists) {
            return ExtensionWorkerResponse.builder().message(USER_ALREADY_EXIST).build();
        }
        if (extensionWorkerRequest.getPassword() == null || extensionWorkerRequest.getPassword().isBlank()) {throw new IllegalArgumentException(PASSWORD_CANNOT_BE_NULL);
        }
        Users users = Users.builder()
                .email(extensionWorkerRequest.getEmail())
                .password(passwordEncoder.encode(extensionWorkerRequest.getPassword()))
                .userRole(Roles.EXTENSION_WORKER)
                .build();
        ExtensionWorker extensionWorker = ExtensionWorker.builder()
                .users(users)
                .firstName(extensionWorkerRequest.getFirstName())
                .lastName(extensionWorkerRequest.getLastName())
                .phoneNumber(extensionWorkerRequest.getPhoneNumber())
                .passportPhotograph(extensionWorkerRequest.getPassportPhotograph())
                .build();
        // Generate and save token
        String token = ApplicationUtilities.registrationToken();
        RegistrationToken registrationToken = new RegistrationToken();
        registrationToken.setToken(token);
        registrationToken.setEmail(extensionWorkerRequest.getEmail());
        registrationToken.setCreatedAt(LocalDateTime.now());
        registrationToken.setExpiresAt(LocalDateTime.now().plusDays(1));
        tokenRepository.save(registrationToken);
        // Store in pending registrations
        pendingRegistrations.put(extensionWorkerRequest.getEmail(), extensionWorker);
        String emailText = "<p>Hello " + extensionWorkerRequest.getFirstName() + ",</p>" +
                "<p>Your verification token is:</p>" +
                "<h2>" + token + "</h2>" +
                "<p>Please enter this token in the app to verify your registration.</p>" +
                "<p>This token will expire in 24 hours.</p>";
        eventPublisher.publishEvent(new EmailEvent(this, extensionWorkerRequest.getEmail(),
                "Verify Your Extension Worker Registration", emailText));
        return ExtensionWorkerResponse.builder()
                .message("Verification token sent to email")
                .build();
    }
    @Override
    @Transactional
    public ExtensionWorkerRequest verifyToken(TokenVerificationRequest tokenVerificationRequest) {
        // Verify token
        RegistrationToken token = tokenRepository.findByToken(tokenVerificationRequest.getToken())
                .filter(t -> t.getExpiresAt().isAfter(LocalDateTime.now()))
                .orElseThrow(() -> new IllegalArgumentException(INVALID_TOKEN));
        // Retrieve pending worker
        ExtensionWorker pendingWorker = pendingRegistrations.get(token.getEmail());
        if (pendingWorker == null) {
            throw new IllegalArgumentException("No pending registration found for email: " + token.getEmail());
        }
        // Send pending approval email
        String pendingEmailText = "<p>Hello " + pendingWorker.getFirstName() + ",</p>" +
                "<p>Your registration is now pending, please wait for admin approval. You will be notified once approved.</p>";
        eventPublisher.publishEvent(new EmailEvent(this, token.getEmail(),
                "Registration Pending Approval", pendingEmailText));
        // Return worker details for admin review
        return ExtensionWorkerRequest.builder()
                .email(pendingWorker.getUsers().getEmail())
                .firstName(pendingWorker.getFirstName())
                .lastName(pendingWorker.getLastName())
                .phoneNumber(pendingWorker.getPhoneNumber())
                .passportPhotograph(pendingWorker.getPassportPhotograph())
                .build();
    }
    @Transactional
    public ExtensionWorkerRequest getPendingWorkerDetails(String email) {
        ExtensionWorker pendingWorker = pendingRegistrations.get(email);
        if (pendingWorker == null) {
            throw new IllegalArgumentException("No pending registration found for email: " + email);
        }
        return ExtensionWorkerRequest.builder()
                .email(pendingWorker.getUsers().getEmail())
                .firstName(pendingWorker.getFirstName())
                .lastName(pendingWorker.getLastName())
                .phoneNumber(pendingWorker.getPhoneNumber())
                .passportPhotograph(pendingWorker.getPassportPhotograph())
                .build();
    }
    @Transactional
    public String approveOrRejectExtensionWorker(ExtensionWorkerAdminDecision decision) {
        String email = decision.getEmail();
        Stamp action = decision.getAction();
        ExtensionWorker pendingWorker = pendingRegistrations.get(email);
        if (pendingWorker == null) {throw new IllegalArgumentException(NO_PENDING_REGISTRATION + email);}
        if (action == Stamp.APPROVE) {extensionWorkerRepository.save(pendingWorker);
            pendingRegistrations.remove(email);
            String approvalEmailText = "<p>Hello " + pendingWorker.getFirstName() + ",</p>" +
                    "<p>Your registration as an Extension Worker has been approved!</p>";
            eventPublisher.publishEvent(new EmailEvent(this, email, "Registration Approved", approvalEmailText));
            return "Extension Worker approved and saved to database";
        } else if (action == Stamp.REJECT) {
            pendingRegistrations.remove(email);
            String rejectionEmailText = "<p>Hello " + pendingWorker.getFirstName() + ",</p>" +
                    "<p>Your registration as an Extension Worker has been rejected.</p>";
            eventPublisher.publishEvent(new EmailEvent(this, email, "Registration Rejected", rejectionEmailText));
            return "Extension Worker registration rejected";
        }
        throw new IllegalArgumentException("Invalid action: " + action);
    }

    public List<ExtensionWorkerRequest> getAllPendingWorkers() {
        return pendingRegistrations.values().stream()
                .map(worker -> ExtensionWorkerRequest.builder()
                        .email(worker.getUsers().getEmail())
                        .firstName(worker.getFirstName())
                        .lastName(worker.getLastName())
                        .phoneNumber(worker.getPhoneNumber())
                        .passportPhotograph(worker.getPassportPhotograph())
                        .build())
                .toList();
    }

}