package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.registrationRequest.InvestorRegistrationRequest;
import com.oracleous.extention_manager.dto.requests.readRequest.InvestorGetRequest;
import com.oracleous.extention_manager.dto.response.registrationResponse.InvestorRegistrationResponse;
import com.oracleous.extention_manager.services.investorServices.InvestorReadPackage.GetInvestorDetails;
import com.oracleous.extention_manager.services.investorServices.InvestorRegistration.InvestorServiceReg;
//import com.oracleous.extention_manager.services.investorServices.InvestorRetrieveAgriBusiness.InvestorViewAgriBusiness;
import com.oracleous.extention_manager.services.investorServices.investorViewFarmerBusiness.InvestorViewFarmerBusinessMethod;
import com.oracleous.extention_manager.services.investorServices.investorViewFarmers.InvestorViewAllFarmers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/investor")
@RequiredArgsConstructor
@Tag(name = "Investor registering and retrieving API", description = "Registering and Retrieving investor details")
public class InvestorController {
    private final InvestorServiceReg investorServiceReg;
    private final GetInvestorDetails getInvestorDetails;
//    private final InvestorViewAgriBusiness investorViewAgriBusiness ;
    private final InvestorViewAllFarmers investorViewAllFarmers;
    private final InvestorViewFarmerBusinessMethod investorViewFarmerBusinessMethod;
    

    @Operation(
            summary = "Investor Registration",
            description = "Registers an investor using the provided request details."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Investor successfully registered",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = InvestorRegistrationResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request or registration error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            )
    })
    @PostMapping("/registration")
    public ResponseEntity<?> investorRegistration(@RequestBody InvestorRegistrationRequest investorRegistrationRequest) {
        try {
            return new ResponseEntity<>(investorServiceReg.investorRegistration(investorRegistrationRequest), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            summary = "Find an Investor",
            description = "Retrieves investor details based on the request parameters."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Investor details retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = InvestorRegistrationResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            )
    })
    @GetMapping("/find_investor")
    public ResponseEntity<?> findInvestor(@RequestBody InvestorGetRequest investorGetRequest) {
        try {
            return new ResponseEntity<>(getInvestorDetails.getInvestorDetails(investorGetRequest), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/find_agriBusiness")
//    public ResponseEntity <?> findAgriBusiness() {
//        try{
//            return new ResponseEntity<>(investorViewAgriBusiness.agriBusinessResponse(), HttpStatus.OK);
//        }catch (Exception exception){
//            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }


    @GetMapping("check_all_farmers")
    public ResponseEntity <?> checkAllFarmers(){
        try{
            return new ResponseEntity<>(investorViewAllFarmers.getAllFarmers(), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/check_agriBusiness_link_with_farmer/{farmerId}")
    public ResponseEntity<?> getAgriBusinessByFarmer(@PathVariable Long farmerId) {
        try {
            return new ResponseEntity<>(investorViewFarmerBusinessMethod.getAgriBusinessByFarmer(farmerId), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

