package com.oracleous.extention_manager.services.investorServices.InvestorRegistration;

import com.oracleous.extention_manager.data.model.Investor;
import com.oracleous.extention_manager.data.repositories.InvestorRepository;
import com.oracleous.extention_manager.dto.requests.registrationRequest.InvestorRegistrationRequest;
import com.oracleous.extention_manager.dto.response.registrationResponse.InvestorRegistrationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import static com.oracleous.extention_manager.utilities.ApplicationUtilities.*;

@Service
@AllArgsConstructor
public class InvestorServiceRegImplementation implements InvestorServiceReg {

    private final InvestorRepository investorRepository;

    @Override
    public InvestorRegistrationResponse investorRegistration(InvestorRegistrationRequest investorRegistrationRequest) {

        boolean investorExist = investorRepository.existsByEmailAndPhoneNumber(
                investorRegistrationRequest.getEmail(),
                investorRegistrationRequest.getPhoneNumber()
        );
        if(investorExist){
            return InvestorRegistrationResponse.builder().
                    responseMessage(INVESTOR_ALREADY_EXIST)
                    .build();
        }

        Investor investor = Investor.builder().
                firstName(investorRegistrationRequest.getFirstName()).
                lastName(investorRegistrationRequest.getLastName()).
                phoneNumber(investorRegistrationRequest.getPhoneNumber()).
                email(investorRegistrationRequest.getEmail()).
                password(investorRegistrationRequest.getPassword()).
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
