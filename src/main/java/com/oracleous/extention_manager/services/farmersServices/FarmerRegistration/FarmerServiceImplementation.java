package com.oracleous.extention_manager.services.farmersServices.FarmerRegistration;

import com.oracleous.extention_manager.data.model.Farmer;
import com.oracleous.extention_manager.data.model.Roles;
import com.oracleous.extention_manager.data.model.Users;
import com.oracleous.extention_manager.data.repositories.FarmersRepository;
import com.oracleous.extention_manager.data.repositories.UserRepository;
import com.oracleous.extention_manager.dto.requests.registrationRequest.TokenVerificationRequest;
import com.oracleous.extention_manager.dto.requests.registrationRequest.FarmersRegistrationRequest;
import com.oracleous.extention_manager.dto.response.registrationResponse.FarmerInfo;
import com.oracleous.extention_manager.dto.response.registrationResponse.FarmerResponse;
import com.oracleous.extention_manager.email.EmailEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static com.oracleous.extention_manager.utilities.ApplicationUtilities.*;

@Service
@RequiredArgsConstructor
public class FarmerServiceImplementation implements FarmersService {

    private static final Logger log = LoggerFactory.getLogger(FarmerServiceImplementation.class);
    private final BCryptPasswordEncoder passwordEncoder ;
    private final FarmersRepository farmersRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final ConcurrentHashMap<String, Farmer> pendingRegistrations = new ConcurrentHashMap<>();
    private static final long TOKEN_EXPIRATION_MINUTES = 15;
    private final UserRepository userRepository;

    @Override
    public FarmerResponse registerFarmer(FarmersRegistrationRequest request) {
        boolean farmerExists = farmersRepository.existsByEmailOrNationalIdOrPhoneNumber(
                request.getEmail(), request.getNationalId(), request.getPhoneNumber()
        );
        if (farmerExists) {
            log.warn(FARMER_EXIST ,request.getEmail(), request.getNationalId(), request.getPhoneNumber());
            return FarmerResponse.builder()
                .responseCode(FARMER_EXIST_CODE)
                .responseMessage(FARMER_ALREADY_EXIST)
                .farmerInfo(null)
                .build();
        }
        Users users = Users.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(Roles.FARMER)
                .build();
        Farmer newFarmer = Farmer.builder()
                .users(users)
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
        pendingRegistrations.put(token, newFarmer);
//        log.info("Stored pending registration for token: {}", token);
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
    public FarmerResponse verifyToken(TokenVerificationRequest tokenVerificationRequest) {
        Farmer farmer = pendingRegistrations.get(tokenVerificationRequest.getToken());
        if (farmer == null) {
        return FarmerResponse.builder()
                .responseCode(INVALID_TOKEN_CODE)
                .responseMessage(INVALID_OR_EXPIRE_TOKEN)
                .farmerInfo(null)
                .build();
        }
        if (System.currentTimeMillis() > farmer.getTokenExpiration()) {
            pendingRegistrations.remove(tokenVerificationRequest.getToken());
            return FarmerResponse.builder()
                .responseCode(TOKEN_EXPIRED_CODE)
                .responseMessage(TOKEN_HAS_EXPIRED_PLEASE_RE_REGISTER)
                .farmerInfo(null)
                .build();
    }
        Farmer savedFarmer = farmersRepository.save(farmer);
        pendingRegistrations.remove(tokenVerificationRequest.getToken());
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