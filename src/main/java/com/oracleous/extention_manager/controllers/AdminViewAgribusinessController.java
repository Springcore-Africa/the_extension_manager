package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.services.agriBusinessServices.AdminReadAllAgriBusiness.AdminToViewAllAgriBusinessMethod;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Admin")
@RequiredArgsConstructor
public class AdminViewAgribusinessController {
    private final AdminToViewAllAgriBusinessMethod adminToViewAllAgriBusinessMethod;


    @GetMapping("/view/agriBusiness")
    public ResponseEntity <?> viewAgriBusiness(){
        try{
            return new ResponseEntity<>(adminToViewAllAgriBusinessMethod.adminViewAgriBusinessResponse(), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
