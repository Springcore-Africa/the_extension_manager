package com.oracleous.extention_manager.services.investorServices.InvestorReadPackage;

import com.oracleous.extention_manager.data.model.Investor;
import com.oracleous.extention_manager.data.repositories.InvestorRepository;
import com.oracleous.extention_manager.dto.requests.readRequest.InvestorGetRequest;
import com.oracleous.extention_manager.dto.response.readResponse.FullName;
import com.oracleous.extention_manager.dto.response.readResponse.InvestorGetResponse;
import com.oracleous.extention_manager.exceptions.InvestorNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.oracleous.extention_manager.utilities.ApplicationUtilities.EMAIL_OR_PHONE_NUMBER_MUST_BE_PROVIDED;
import static com.oracleous.extention_manager.utilities.ApplicationUtilities.EMAIL_OR_PHONE_NUMBER_NOT_FOUND;

@Service
@AllArgsConstructor
public class GetInvestorDetailsMethod implements GetInvestorDetails{
    private final InvestorRepository investorRepository ;

    @Override
    public InvestorGetResponse getInvestorDetails(InvestorGetRequest getInvestorDetailsRequest) {
        String email = getInvestorDetailsRequest.getEmail();
        String phoneNumber = getInvestorDetailsRequest.getPhoneNumber();

        if ((email == null || email.isEmpty()) && (phoneNumber == null || phoneNumber.isEmpty())) {throw new InvestorNotFoundException(EMAIL_OR_PHONE_NUMBER_MUST_BE_PROVIDED);}
        Investor investor = investorRepository.findByUsersEmailOrPhoneNumber(email, phoneNumber).orElseThrow(()-> new InvestorNotFoundException(EMAIL_OR_PHONE_NUMBER_NOT_FOUND));
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
