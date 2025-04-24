package com.oracleous.extention_manager.services.farmersServices.FarmerRegistration;

import com.oracleous.extention_manager.data.model.Farmer;
import com.oracleous.extention_manager.data.repositories.FarmersRepository;
import com.oracleous.extention_manager.dto.requests.registrationRequest.FarmerVerifyTokenRequest;
import com.oracleous.extention_manager.dto.requests.registrationRequest.FarmersRegistrationRequest;
import com.oracleous.extention_manager.dto.response.registrationResponse.FarmerInfo;
import com.oracleous.extention_manager.dto.response.registrationResponse.FarmerResponse;
import com.oracleous.extention_manager.email.EmailEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static com.oracleous.extention_manager.utilities.ApplicationUtilities.*;

@Service
@RequiredArgsConstructor
public class FarmerServiceImplementation implements FarmersService {

    private static final Logger log = LoggerFactory.getLogger(FarmerServiceImplementation.class);

    private final FarmersRepository farmersRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final ConcurrentHashMap<String, Farmer> pendingRegistrations = new ConcurrentHashMap<>();
    private static final long TOKEN_EXPIRATION_MINUTES = 15;

    @Override
    public FarmerResponse registerFarmer(FarmersRegistrationRequest request) {
        log.info("Registering farmer with email: {}", request.getEmail());

        // Check if farmer already exists
        boolean farmerExists = farmersRepository.existsByEmailOrNationalIdOrPhoneNumber(
                request.getEmail(), request.getNationalId(), request.getPhoneNumber()
        );

        if (farmerExists) {
            log.warn("Farmer already exists with email: {}, nationalId: {}, or phoneNumber: {}",
                    request.getEmail(), request.getNationalId(), request.getPhoneNumber());
            return FarmerResponse.builder()
                    .responseCode(FARMER_EXIST_CODE)
                    .responseMessage(FARMER_ALREADY_EXIST)
                    .farmerInfo(null)
                    .build();
        }

        Farmer newFarmer = Farmer.builder()
                .email(request.getEmail())
                .nationalId(request.getNationalId())
                .phoneNumber(request.getPhoneNumber())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .description(request.getDescription())
                .lgaOfOrigin(request.getLgaOfOrigin())
                .maritalStatus(request.getMaritalStatus())
                .numberOfChildren(request.getNumberOfChildren())
                .regNumber(generateRegNumber())
                .residentialAddress(request.getResidentialAddress())
                .ninSlip(request.getNinSlip())
                .passportPhotograph(request.getPassportPhotograph())
                .lastEducationalCertificate(request.getLastEducationalCertificate())
                .birthCertificate(request.getBirthCertificate())
                .build();

        String token = registrationToken();
        newFarmer.setVerificationToken(token);
        newFarmer.setTokenExpiration(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(TOKEN_EXPIRATION_MINUTES));

        // I store this in pending registrations
        pendingRegistrations.put(token, newFarmer);
        log.info("Stored pending registration for token: {}", token);

        String subject = "Complete Your Farmer Registration";
        String emailContent = String.format(
                "<p>Dear %s,</p>" +
                        "<p>Please enter the following token to complete your registration:</p>" +
                        "<h3>%s</h3>" +
                        "<p>This token will expire in %d minutes.</p>",
                request.getFirstName(), token, TOKEN_EXPIRATION_MINUTES
        );

        eventPublisher.publishEvent(new EmailEvent(this, request.getEmail(), subject, emailContent));
        log.info("Email event published for farmer: {}", request.getEmail());

        return FarmerResponse.builder()
                .responseCode(TOKEN_SENT_CODE)
                .responseMessage("Registration initiated. Please check your email for the verification token.")
                .farmerInfo(null)
                .build();
    }

    @Override
    public FarmerResponse verifyToken(FarmerVerifyTokenRequest farmerVerifyTokenRequest) {
        log.info("Verifying token: {}", farmerVerifyTokenRequest);

        Farmer farmer = pendingRegistrations.get(farmerVerifyTokenRequest.getToken());
        if (farmer == null) {
            log.warn("No pending registration found for token: {}", farmerVerifyTokenRequest);
            return FarmerResponse.builder()
                    .responseCode(INVALID_TOKEN_CODE)
                    .responseMessage("Invalid or expired token.")
                    .farmerInfo(null)
                    .build();
        }

        if (System.currentTimeMillis() > farmer.getTokenExpiration()) {
            pendingRegistrations.remove(farmerVerifyTokenRequest.getToken());
            log.warn("Token expired for token: {}", farmerVerifyTokenRequest);
            return FarmerResponse.builder()
                    .responseCode(TOKEN_EXPIRED_CODE)
                    .responseMessage("Token has expired. Please register again.")
                    .farmerInfo(null)
                    .build();
        }

        Farmer savedFarmer = farmersRepository.save(farmer);
        pendingRegistrations.remove(farmerVerifyTokenRequest.getToken());
        log.info("Farmer saved to database with regNumber: {}", savedFarmer.getRegNumber());

        return FarmerResponse.builder()
                .responseCode(ACCOUNT_CREATED_CODE)
                .responseMessage(ACCOUNT_CREATED_MESSAGE)
                .farmerInfo(FarmerInfo.builder()
                        .farmersName(savedFarmer.getFirstName() + " " + savedFarmer.getLastName())
                        .farmersRegNumber(savedFarmer.getRegNumber())
                        .build())
                .build();
    }
}