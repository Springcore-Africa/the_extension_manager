package com.oracleous.extention_manager.services.farmersServices.FarmerRegistration;

import com.oracleous.extention_manager.data.model.Farmer;
import com.oracleous.extention_manager.data.repositories.FarmersRepository;
import com.oracleous.extention_manager.dto.requests.registrationRequest.FarmersRegistrationRequest;
import com.oracleous.extention_manager.dto.response.registrationResponse.FarmerInfo;
import com.oracleous.extention_manager.dto.response.registrationResponse.FarmerResponse;
import com.oracleous.extention_manager.email.EmailEvent;
import com.oracleous.extention_manager.utilities.ApplicationUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import static com.oracleous.extention_manager.utilities.ApplicationUtilities.*;

@Service
@RequiredArgsConstructor
public class FarmerServiceImplementation implements FarmersService {

    final FarmersRepository farmersRepository;
    private final ApplicationEventPublisher eventPublisher;


    public FarmerResponse registerFarmer(FarmersRegistrationRequest request, String tokenProvided)  {
        String expectedToken = TemporaryTokenStore.tokenStore.get(request.getEmail());

        if (expectedToken == null || !expectedToken.equals(tokenProvided)) {
            return FarmerResponse.builder()
                    .responseCode(INVALID_TOKEN)
                    .responseMessage("Invalid or expired token.")
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
                .regNumber(ApplicationUtilities.generateRegNumber())
                .residentialAddress(request.getResidentialAddress())
                .ninSlip(request.getNinSlip())
                .passportPhotograph(request.getPassportPhotograph())
                .lastEducationalCertificate(request.getLastEducationalCertificate())
                .birthCertificate(request.getBirthCertificate())
                .isVerified(true)
                .build();

        Farmer saved = farmersRepository.save(newFarmer);
        TemporaryTokenStore.tokenStore.remove(request.getEmail());

        return FarmerResponse.builder()
                .responseCode(ACCOUNT_CREATED_CODE)
                .responseMessage(ACCOUNT_CREATED_MESSAGE)
                .farmerInfo(FarmerInfo.builder()
                        .farmersName(saved.getFirstName() + " " + saved.getLastName())
                        .farmersRegNumber(saved.getRegNumber())
                        .build())
                .build();



//        boolean farmerExists = farmersRepository.existsByEmailOrNationalIdOrPhoneNumber(
//                request.getEmail(),
//                request.getNationalId(),
//                request.getPhoneNumber()
//        );
//
//        if (farmerExists) {
//            return FarmerResponse.builder()
//                    .responseCode(FARMER_EXIST_CODE)
//                    .responseMessage(FARMER_ALREADY_EXIST)
//                    .farmerInfo(null)
//                    .build();
//        }
//
//        Farmer newFarmer = Farmer.builder()
//                .email(request.getEmail())
//                .nationalId(request.getNationalId())
//                .phoneNumber(request.getPhoneNumber())
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .dateOfBirth(request.getDateOfBirth())
//                .gender(request.getGender())
//                .description(request.getDescription())
//                .lgaOfOrigin(request.getLgaOfOrigin())
//                .maritalStatus(request.getMaritalStatus())
//                .numberOfChildren(request.getNumberOfChildren())
//                .regNumber(ApplicationUtilities.generateRegNumber())
//                .residentialAddress(request.getResidentialAddress())
//                .ninSlip(request.getNinSlip())
//                .passportPhotograph(request.getPassportPhotograph())
//                .lastEducationalCertificate(request.getLastEducationalCertificate())
//                .birthCertificate(request.getBirthCertificate())
//                .build();
//
//                String token  = registrationToken() ;
//                newFarmer.setVerificationToken(token);
//
//                String subject = "Hi Farmer" ;
//                String test = "<p> Please kindly enter the digit below to complete your registration :</p>" ;
//
//                eventPublisher.publishEvent(new EmailEvent(this, request.getEmail(), subject, test));
//                Farmer savedFarmer = farmersRepository.save(newFarmer);
//
//        return FarmerResponse.builder()
//                .responseCode(ACCOUNT_CREATED_CODE)
//                .responseMessage(ACCOUNT_CREATED_MESSAGE)
//                .farmerInfo(FarmerInfo.builder()
//                        .farmersName(savedFarmer.getFirstName() +" "+ savedFarmer.getLastName())
//                        .farmersRegNumber(savedFarmer.getRegNumber())
//                        .build())
//                .build();
    }

    public FarmerResponse initiateRegistration(FarmersRegistrationRequest farmersRegistrationRequest) {

        if (farmersRepository.existsByEmail(farmersRegistrationRequest.getEmail())) {
            return FarmerResponse.builder()
                    .responseCode(FARMER_EXIST_CODE)
                    .responseMessage(FARMER_ALREADY_EXIST)
                    .build();
        }

        String token = ApplicationUtilities.registrationToken();
        TemporaryTokenStore.tokenStore.put(farmersRegistrationRequest.getEmail(), token);

        String subject = "Email Verification";
        String content = "<p>Enter this code to verify your registration: <b>" + token + "</b></p>";
        eventPublisher.publishEvent(new EmailEvent(this, farmersRegistrationRequest.getEmail(), subject, content));

        return FarmerResponse.builder()
                .responseCode(ACCOUNT_TOKEN_SENT)
                .responseMessage("Verification code sent to email.")
                .build();
    }


}
