package com.oracleous.extention_manager.services.investorServices.InvestorRegistration;

import com.oracleous.extention_manager.data.model.Investor;
import com.oracleous.extention_manager.data.model.Roles;
import com.oracleous.extention_manager.data.model.Users;
import com.oracleous.extention_manager.data.repositories.InvestorRepository;
import com.oracleous.extention_manager.dto.requests.registrationRequest.InvestorRegistrationRequest;
import com.oracleous.extention_manager.dto.response.registrationResponse.InvestorRegistrationResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import static com.oracleous.extention_manager.utilities.ApplicationUtilities.*;

@Service
@AllArgsConstructor
public class InvestorServiceRegImplementation implements InvestorServiceReg {

    private final InvestorRepository investorRepository;
    private final BCryptPasswordEncoder passwordEncoder ;

    @Override
    public InvestorRegistrationResponse investorRegistration(InvestorRegistrationRequest investorRegistrationRequest) {

//        boolean investorExist = investorRepository.existsByUsersEmailAndUsersPhoneNumber(
//                investorRegistrationRequest.getEmail(),
//                investorRegistrationRequest.getPhoneNumber()
//        );
        boolean investorExist = investorRepository.existsByUsersEmailAndPhoneNumber(
                investorRegistrationRequest.getEmail(),
                investorRegistrationRequest.getPhoneNumber()
        );

        if(investorExist){
            return InvestorRegistrationResponse.builder().
                    responseMessage(INVESTOR_ALREADY_EXIST)
                    .build();
        }
        Users user = Users.builder()
                .email(investorRegistrationRequest.getEmail())
                .password(passwordEncoder.encode(investorRegistrationRequest.getPassword()))
                .userRole(Roles.INVESTOR)
                .build();

        Investor investor = Investor.builder().
                users(user).
                firstName(investorRegistrationRequest.getFirstName()).
                lastName(investorRegistrationRequest.getLastName()).
                phoneNumber(investorRegistrationRequest.getPhoneNumber()).
                shortBio(investorRegistrationRequest.getShortBio()).
                passportPhotograph(investorRegistrationRequest.getPassportPhotograph()).
                build();
        Investor savedInvestor = investorRepository.save(investor);

        return InvestorRegistrationResponse.builder().
                FirstName(savedInvestor.getFirstName()).
                LastName(savedInvestor.getLastName()).
                responseMessage(INVESTOR_CREATED_MESSAGE).
                build();
    }
}
