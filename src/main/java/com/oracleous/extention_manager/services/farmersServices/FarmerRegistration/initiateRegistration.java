//import com.oracleous.extention_manager.dto.requests.registrationRequest.FarmersRegistrationRequest;
//import com.oracleous.extention_manager.dto.response.registrationResponse.FarmerResponse;
//import com.oracleous.extention_manager.email.EmailEvent;
//import com.oracleous.extention_manager.services.farmersServices.FarmerRegistration.TemporaryTokenStore;
//import com.oracleous.extention_manager.utilities.ApplicationUtilities;
//
//import static com.oracleous.extention_manager.utilities.ApplicationUtilities.*;
//
//public FarmerResponse initiateRegistration(FarmersRegistrationRequest farmersRegistrationRequest) {
//
//    if (farmersRepository.existsByEmail(farmersRegistrationRequest.getEmail())) {
//        return FarmerResponse.builder()
//                .responseCode(FARMER_EXIST_CODE)
//                .responseMessage(FARMER_ALREADY_EXIST)
//                .build();
//    }
//
//    String token = ApplicationUtilities.registrationToken();
//    TemporaryTokenStore.tokenStore.put(farmersRegistrationRequest.getEmail(), token);
//
//    String subject = "Email Verification";
//    String content = "<p>Enter this code to verify your registration: <b>" + token + "</b></p>";
//    eventPublisher.publishEvent(new EmailEvent(this, farmersRegistrationRequest.getEmail(), subject, content));
//
//    return FarmerResponse.builder()
//            .responseCode(ACCOUNT_TOKEN_SENT)
//            .responseMessage("Verification code sent to email.")
//            .build();
//}
