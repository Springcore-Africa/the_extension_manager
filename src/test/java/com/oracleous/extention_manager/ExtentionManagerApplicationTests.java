package com.oracleous.extention_manager;

import com.oracleous.extention_manager.data.model.Investor;
import com.oracleous.extention_manager.dto.requests.InvestorRegistrationRequest;
import com.oracleous.extention_manager.dto.response.InvestorRegistrationResponse;
import com.oracleous.extention_manager.services.investorServices.InvestorServiceReg;
import com.oracleous.extention_manager.services.investorServices.InvestorServiceRegImplementation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class ExtentionManagerApplicationTests {

	@Autowired
	private InvestorServiceReg investorServiceReg;

	@Test
	public void testThatInvestorCanRegister() {
		InvestorRegistrationRequest investorRegistrationRequest = new InvestorRegistrationRequest();
		Investor investor = new Investor();
		investor.setFirstName("Taoreed");
		investor.setLastName("Olawale");
		investor.setEmail("john@doe.com");
		investor.setPassword("1234");
		investor.setPhoneNumber("1234567890");
		investor.setPassportPhotograph("img");
		investor.setShortBio("I am an investor");
		investorRegistrationRequest.setFirstName("Taoreed");
		investorRegistrationRequest.setLastName(investor.getLastName());
		investorRegistrationRequest.setEmail(investor.getEmail());
		investorRegistrationRequest.setPassword(investor.getPassword());
		investorRegistrationRequest.setPassportPhotograph(investor.getPassportPhotograph());
		investorRegistrationRequest.setPhoneNumber(investor.getPhoneNumber());
		investorRegistrationRequest.setShortBio(investor.getShortBio());

		InvestorRegistrationResponse investorRegistrationResponse = investorServiceReg.investorRegistration(investorRegistrationRequest);
		investorRegistrationResponse.setResponseMessage("Investor With These details Already Exist");
		investorRegistrationResponse.setFirstName(investor.getFirstName());
		investorRegistrationResponse.setLastName(investor.getLastName());
//		when(investorServiceReg.investorRegistration(investorRegistrationRequest)).thenReturn(investorRegistrationResponse);

		InvestorRegistrationResponse investorRegistrationResponse1 = investorServiceReg.investorRegistration(investorRegistrationRequest);
		assertEquals(investorRegistrationResponse.getResponseMessage(), "Investor With These details Already Exist", investorRegistrationResponse1.getResponseMessage());
	}

}
