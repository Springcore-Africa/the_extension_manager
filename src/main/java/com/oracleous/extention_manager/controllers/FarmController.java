package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.farmPackage.FarmRequest;
import com.oracleous.extention_manager.services.farmService.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/farms")
@RequiredArgsConstructor
public class FarmController {
    private final FarmService farmService;

    @PostMapping
    public ResponseEntity<?> registerFarm(@ModelAttribute FarmRequest farmRequest) {
        try{
            return new ResponseEntity<>(farmService.farmResponse(farmRequest), HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
