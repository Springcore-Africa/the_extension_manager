package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.InvestorRegistrationRequest;
import com.oracleous.extention_manager.dto.requests.readRequest.InvestorGetRequest;
import com.oracleous.extention_manager.services.investorServices.InvestorReadPackage.GetInvestorDetails;
import com.oracleous.extention_manager.services.investorServices.InvestorRegistration.InvestorServiceReg;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class InvestorController {
    private final InvestorServiceReg investorServiceReg;
    private final GetInvestorDetails getInvestorDetails ;


    @PostMapping("/investor_registration")
    public ResponseEntity<?> investorRegistration(@RequestBody InvestorRegistrationRequest investorRegistrationRequest){
        try {
            return new ResponseEntity<>(investorServiceReg.investorRegistration(investorRegistrationRequest), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find_investor/")
    public ResponseEntity<?> findInvestor(@RequestBody InvestorGetRequest investorGetRequest){
        try{
            return new ResponseEntity<>(getInvestorDetails.getInvestorDetails(investorGetRequest), HttpStatus.OK);
        }catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}