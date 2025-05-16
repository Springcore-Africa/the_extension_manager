package com.oracleous.extention_manager.controllers;
import com.oracleous.extention_manager.dto.requests.registrationRequest.LocalAdminRegRequest;
import com.oracleous.extention_manager.services.localAdminService.localAdminRegistration.LocalAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/investor")
@RequiredArgsConstructor
public class LocalAdminController {
    private final LocalAdminService localAdminService;

    @PostMapping("/local_admin_registration")


    public ResponseEntity <?> agriBusinessRegistration(@RequestBody LocalAdminRegRequest localAdminRegRequest){
        try{
            return new ResponseEntity<>(localAdminService.localAdminRegistration(localAdminRegRequest), HttpStatus.CREATED);
        }catch (Exception extension){
            return new ResponseEntity<>(extension.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
