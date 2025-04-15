//package com.oracleous.extention_manager.services.superAdminServices.InviteServices;
//
//import com.oracleous.extention_manager.data.repositories.SuperAdminRepository;
//import com.oracleous.extention_manager.dto.requests.requestEmail.AdminRegistrationRequestDto;
//import com.oracleous.extention_manager.dto.response.ResponseToMailSend.ResponseEmail;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import static com.oracleous.extention_manager.utilities.ApplicationUtilities.SUPER_ADMIN_MESSAGE;
//
//@Service
//@AllArgsConstructor
//public class InviteEmailService implements InviteService {
//    private final SuperAdminRepository superAdminRepository;
//
//    @Override
//    public ResponseEmail responsetoAdmin(AdminRegistrationRequestDto adminRegistrationRequestDto) {
//        boolean superAdminExist = superAdminRepository.existsByEmail(
//                adminRegistrationRequestDto.getEmail()
//        );
//
//        if(superAdminExist) {
//            return ResponseEmail.builder().
//                    message(SUPER_ADMIN_MESSAGE).
//                    build();
//        }
//
//        AdminRegistrationRequestDto.builder().
//                email(adminRegistrationRequestDto.getEmail()).
//                build();
//
//        return null;
//    }
//}
