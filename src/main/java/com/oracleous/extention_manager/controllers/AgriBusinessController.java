package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.AgriBusinessRegRequest;
import com.oracleous.extention_manager.dto.requests.readRequest.AgricGetRequest;
import com.oracleous.extention_manager.exceptions.BusinessAlreadyExistsException;
import com.oracleous.extention_manager.exceptions.FarmerNotFoundException;
import com.oracleous.extention_manager.services.agriBusinessServices.AgricBusinessReadPackage.GetAgricBusinessDetailsMethod;
import com.oracleous.extention_manager.services.agriBusinessServices.AgricBusinessRegistration.AgriBusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/agri_business")
public class AgriBusinessController {
    final AgriBusinessService agriBusinessService;
    final GetAgricBusinessDetailsMethod agricBusinessDetailsMethod ;

    @PostMapping("/agri_business_registration")
    public ResponseEntity <?> agriBusinessRegistration(@RequestBody AgriBusinessRegRequest agriBusinessRegRequest) throws BusinessAlreadyExistsException, FarmerNotFoundException {
       try{
           return new ResponseEntity<>(agriBusinessService.registerAgriBusiness(agriBusinessRegRequest), HttpStatus.CREATED);
       }catch (BusinessAlreadyExistsException exception){
           return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping("/find_agriBusiness/")
    public ResponseEntity <?> findAgriBusiness(@RequestBody AgricGetRequest agricGetRequest) {
        try{
            return new ResponseEntity<>(agricBusinessDetailsMethod.getAgricBusinessDetails(agricGetRequest), HttpStatus.FOUND);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/find_agriBusiness/")
//    public ResponseEntity <?> findAgriBusiness(    @RequestParam(required = false) String email,
//                                                   @RequestParam(required = false) String phoneNumber) {
//        try{
//            AgricGetRequest agric = new AgricGetRequest();
//            agric.setEmail(email);
//            agric.setPhoneNumber(phoneNumber);
//            return new ResponseEntity<>(agricBusinessDetailsMethod.getAgricBusinessDetails(agric), HttpStatus.FOUND);
//        }catch (Exception exception){
//            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
}
