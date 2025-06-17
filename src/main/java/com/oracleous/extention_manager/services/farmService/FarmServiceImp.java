package com.oracleous.extention_manager.services.farmService;

import com.oracleous.extention_manager.data.model.Farm;
import com.oracleous.extention_manager.data.model.Farmer;
import com.oracleous.extention_manager.data.model.UserPrincipal;
import com.oracleous.extention_manager.data.model.Users;
import com.oracleous.extention_manager.data.repositories.FarmRepository;
import com.oracleous.extention_manager.data.repositories.FarmersRepository;
import com.oracleous.extention_manager.dto.requests.farmPackage.FarmRequest;
import com.oracleous.extention_manager.dto.response.farmResponse.FarmResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FarmServiceImp implements FarmService {
    private final FarmersRepository farmersRepository;
    private final FarmRepository farmRepository;

    @Override
    public FarmResponse farmResponse(FarmRequest farmRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){throw new IllegalArgumentException("Authentication required");}
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Users username = userPrincipal.users();
        if(username == null){throw new IllegalArgumentException("Username required");}
        Farmer farmer = farmersRepository.findByUsers(username).orElseThrow(()-> new IllegalArgumentException("Farmer not found"));


        Farm farm = Farm.builder()
                .farmer(farmer)
                .farmName(farmRequest.getFarmName())
                .farmSize(farmRequest.getFarmSize())
                .cropPlanted(farmRequest.getCropPlanted())
                .location(farmRequest.getLocation())
//                .farmPicture()
                .build();
        farmRepository.save(farm);
        return FarmResponse.builder()
                .message("Farmer registration successful")
                .build();
    }
}
