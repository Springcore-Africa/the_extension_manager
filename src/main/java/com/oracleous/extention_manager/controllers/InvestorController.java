package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.registrationRequest.InvestorRegistrationRequest;
import com.oracleous.extention_manager.dto.requests.readRequest.InvestorGetRequest;
import com.oracleous.extention_manager.dto.response.registrationResponse.InvestorRegistrationResponse;
import com.oracleous.extention_manager.services.investorServices.InvestorReadPackage.GetInvestorDetails;
import com.oracleous.extention_manager.services.investorServices.InvestorRegistration.InvestorServiceReg;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Investor Registration and to find investor details", description = "API for registering SuperAdmin")
public class InvestorController {
    private final InvestorServiceReg investorServiceReg;
    private final GetInvestorDetails getInvestorDetails ;

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
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping("/investor_registration")
    public ResponseEntity<?> investorRegistration(@RequestBody InvestorRegistrationRequest investorRegistrationRequest){
        try {
            return new ResponseEntity<>(investorServiceReg.investorRegistration(investorRegistrationRequest), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            summary = "Find an investor",
            description = "Retrieves investor details based on the request parameters."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "it will return the user details"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/find_investor/")
    public ResponseEntity<?> findInvestor(@RequestBody InvestorGetRequest investorGetRequest){
        try{
            return new ResponseEntity<>(getInvestorDetails.getInvestorDetails(investorGetRequest), HttpStatus.OK);
        }catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}