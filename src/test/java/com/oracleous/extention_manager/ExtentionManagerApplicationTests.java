package com.oracleous.extention_manager;

import com.oracleous.extention_manager.data.model.Farmer;
import com.oracleous.extention_manager.data.model.Gender;
import com.oracleous.extention_manager.data.model.Investor;
import com.oracleous.extention_manager.data.model.MaritalStatus;
import com.oracleous.extention_manager.data.repositories.AgriBusinessRepository;
import com.oracleous.extention_manager.data.repositories.FarmersRepository;
import com.oracleous.extention_manager.dto.requests.AgriBusinessRegRequest;
import com.oracleous.extention_manager.dto.requests.FarmersRegistrationRequest;
import com.oracleous.extention_manager.dto.requests.InvestorRegistrationRequest;
import com.oracleous.extention_manager.dto.requests.readRequest.AgricGetRequest;
import com.oracleous.extention_manager.dto.requests.readRequest.FarmerGetRequest;
import com.oracleous.extention_manager.dto.requests.readRequest.InvestorGetRequest;
import com.oracleous.extention_manager.dto.response.InvestorRegistrationResponse;
import com.oracleous.extention_manager.dto.response.readResponse.AgricGetResponse;
import com.oracleous.extention_manager.dto.response.readResponse.FarmerGetResponse;
import com.oracleous.extention_manager.dto.response.readResponse.FullName;
import com.oracleous.extention_manager.dto.response.readResponse.InvestorGetResponse;
import com.oracleous.extention_manager.exceptions.BusinessAlreadyExistsException;
import com.oracleous.extention_manager.exceptions.FarmerNotFoundException;
import com.oracleous.extention_manager.services.agriBusinessServices.AgricBusinessReadPackage.GetAgricBusinessDetails;
import com.oracleous.extention_manager.services.agriBusinessServices.AgricBusinessRegistration.AgriBusinessService;
import com.oracleous.extention_manager.services.farmersServices.FarmerReadPackage.GetFarmerDetailsMethod;
import com.oracleous.extention_manager.services.farmersServices.FarmerRegistration.FarmerServiceImplementation;
import com.oracleous.extention_manager.services.investorServices.InvestorReadPackage.GetInvestorDetailsMethod;
import com.oracleous.extention_manager.services.investorServices.InvestorRegistration.InvestorServiceReg;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class ExtentionManagerApplicationTests {

	@Autowired
	private InvestorServiceReg investorServiceReg;
	@Autowired
	private GetFarmerDetailsMethod getFarmerDetailsMethod;
    @Autowired
    private FarmerServiceImplementation farmerServiceImplementation;
	@Autowired
	private GetInvestorDetailsMethod getInvestorDetailsMethod ;
    @Autowired
    private FarmersRepository farmersRepository;
	@Autowired
	private AgriBusinessService agricBusinessService ;
	@Autowired
	private AgriBusinessRepository agriBusinessRepository ;
	@Autowired
	private GetAgricBusinessDetails getAgricBusinessDetails ;



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



	private void investorRegistration() {
		InvestorRegistrationRequest investorRegistrationRequest = new InvestorRegistrationRequest();
		investorRegistrationRequest.setFirstName("Daniela");
		investorRegistrationRequest.setLastName("grace");
		investorRegistrationRequest.setEmail("taoreed@gmail.com");
		investorRegistrationRequest.setPassword("1234");
		investorRegistrationRequest.setPhoneNumber("1234567890");
		investorRegistrationRequest.setPassportPhotograph("img");
		investorRegistrationRequest.setShortBio("I am an investor");
		investorServiceReg.investorRegistration(investorRegistrationRequest);
	}

	@Test
	public void testThatInvestorCanBefounfAfterRegistration(){
		investorRegistration();
		InvestorGetRequest investorGetRequest = new InvestorGetRequest();
		investorGetRequest.setEmail("taoreed@gmail.com");
		investorGetRequest.setPhoneNumber("1234567890");

		InvestorGetResponse investorGetResponse = getInvestorDetailsMethod.getInvestorDetails(investorGetRequest);
		FullName fullName = investorGetResponse.getFullName();
		assertNotNull(fullName, "Full name should not be null");

		assertEquals("Emails ", investorGetResponse.getEmail(), "taoreed@gmail.com");
		assertEquals("Phone number", investorGetResponse.getPhoneNumber(), "1234567890");
		assertEquals("First names ", fullName.getFirstName(), "Daniela");
		assertEquals("Last names ", fullName.getLastName(), "grace");
	}

	private void farmerResponseRegistration() {
		FarmersRegistrationRequest farmersRegistrationRequest = new FarmersRegistrationRequest();
		farmersRegistrationRequest.setFirstName("Daniela");
		farmersRegistrationRequest.setLastName("grace");
		farmersRegistrationRequest.setEmail("john@doe.com");
		farmersRegistrationRequest.setPassword("1234");
		farmersRegistrationRequest.setPhoneNumber("1234567890");
		farmersRegistrationRequest.setPassportPhotograph("img");
		farmersRegistrationRequest.setDescription("i am a farmer");
		farmersRegistrationRequest.setBirthCertificate("brith certificate");
		farmersRegistrationRequest.setDateOfBirth(LocalDate.ofYearDay(2020, 20).atStartOfDay());
		farmersRegistrationRequest.setLastEducationalCertificate("SSCE");
		farmersRegistrationRequest.setLgaOfOrigin("Lagos");
		farmersRegistrationRequest.setMaritalStatus(MaritalStatus.MARRIED);
		farmersRegistrationRequest.setGender(Gender.MALE);
		farmersRegistrationRequest.setNinSlip("110022334455");
		farmersRegistrationRequest.setNationalId("hou111");
		farmersRegistrationRequest.setRegNumber("05FORT");
		farmersRegistrationRequest.setResidentialAddress("No 5,Gwagwalada Abuja city");
		farmersRegistrationRequest.setStateOfOrigin("Lagos");
		farmerServiceImplementation.registerFarmer(farmersRegistrationRequest);
	}

	@Test
	public void testThatFarmerDetailsCanBeFoundAfterRegistration() {
		farmerResponseRegistration();
		FarmerGetRequest farmerGetRequest = new FarmerGetRequest();
		farmerGetRequest.setPhoneNumber("1234567890");
		farmerGetRequest.setEmail("john@doe.com");

		FarmerGetResponse farmerGetResponse = getFarmerDetailsMethod.getFarmerDetails(farmerGetRequest);
		assertNotNull(farmerGetResponse, "Response should not be null");

		FullName fullName = farmerGetResponse.getFullName();
		assertNotNull(fullName, "Full name should not be null");

		assertEquals("Emails ", farmerGetResponse.getEmail(), "john@doe.com");
		assertEquals("Phone number", farmerGetResponse.getPhoneNumber(), "1234567890");
		assertEquals("First names ", fullName.getFirstName(), "Daniela");
		assertEquals("Last names ", fullName.getLastName(), "grace");
	}

	private void agricBusiness() throws BusinessAlreadyExistsException, FarmerNotFoundException {
		Farmer farmer = new Farmer();
		farmersRepository.save(farmer);
		farmer.setId(farmer.getId());
		farmerResponseRegistration();
		AgriBusinessRegRequest agriBusinessRegRequest = new AgriBusinessRegRequest();
		agriBusinessRegRequest.setBusinessName("technology");
		agriBusinessRegRequest.setBusinessLocationExact("Lagos");
		agriBusinessRegRequest.setRegNumber("LOVE090");
		agriBusinessRegRequest.setBusinessLocationLga("ikeja");
		agriBusinessRegRequest.setAgriculturalProduct("yam");
		agriBusinessRegRequest.setCacCertificate("certification");
		agriBusinessRegRequest.setCacRegistrationDate(LocalDate.of(2020, 10,1).atStartOfDay());
		agriBusinessRegRequest.setNumberOfEmployees(20);
		agriBusinessRegRequest.setMemart("polo");
		agriBusinessRegRequest.setProductPhotos("image");
		agriBusinessRegRequest.setProductDescription("this is an agric business");
		agriBusinessRegRequest.setFarmerId(farmer.getId());
		agricBusinessService.registerAgriBusiness(agriBusinessRegRequest);
	}

	@Test
	public void testThatFarmerCanRegisterForAgrcBusinessAfterRegistration() throws BusinessAlreadyExistsException, FarmerNotFoundException {
//		farmerResponseRegistration();
		agricBusiness();
		AgricGetRequest agricGetRequest = new AgricGetRequest();
		agricGetRequest.setEmail("john@doe.com");
		agricGetRequest.setPhoneNumber("1234567890");

		AgricGetResponse agricGetResponse = getAgricBusinessDetails.getAgricBusinessDetails(agricGetRequest);
		FullName fullName = agricGetResponse.getFullName();
		assertNotNull(fullName, "Full name should not be null");
		assertEquals("Emails ", agricGetResponse.getEmail(), "john@doe.com");
		assertEquals("Phone number", agricGetResponse.getPhoneNumber(), "1234567890");
		assertEquals("First names ", fullName.getFirstName(), "Daniela");
		assertEquals("Last names ", fullName.getLastName(), "grace");
	}

}
