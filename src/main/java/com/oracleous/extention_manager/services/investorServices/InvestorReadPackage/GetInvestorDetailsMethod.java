package com.oracleous.extention_manager.services.investorServices.InvestorReadPackage;

import com.oracleous.extention_manager.data.model.Investor;
import com.oracleous.extention_manager.data.repositories.InvestorRepository;
import com.oracleous.extention_manager.dto.requests.readRequest.InvestorGetRequest;
import com.oracleous.extention_manager.dto.response.readResponse.FullName;
import com.oracleous.extention_manager.dto.response.readResponse.InvestorGetResponse;
import com.oracleous.extention_manager.exceptions.InvestorNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetInvestorDetailsMethod implements GetInvestorDetails{
    private final InvestorRepository investorRepository ;

    @Override
    public InvestorGetResponse getInvestorDetails(InvestorGetRequest getInvestorDetailsRequest) {
        String email = getInvestorDetailsRequest.getEmail();
        String phoneNumber = getInvestorDetailsRequest.getPhoneNumber();

        if ((email == null || email.isEmpty()) && (phoneNumber == null || phoneNumber.isEmpty())) {
            throw new InvestorNotFoundException("Either Email or PhoneNumber must be provided");
        }
        Investor investor = investorRepository.findByUsersEmailOrPhoneNumber(email, phoneNumber).
                orElseThrow(()-> new InvestorNotFoundException("Email or PhoneNumber is not found"));

        FullName fullName = FullName.builder().
                firstName(investor.getFirstName()).
                lastName(investor.getLastName()).
                build();

        return InvestorGetResponse.builder().
                fullName(fullName).
                email(email).
                phoneNumber(phoneNumber).
                passportPhotograph(investor.getPassportPhotograph()).
                shortBio(investor.getShortBio()).
                build();
    }
}
