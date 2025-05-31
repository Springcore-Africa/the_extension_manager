//package com.oracleous.extention_manager.services.investorServices.investorViewFarmers;
//
//import com.oracleous.extention_manager.data.model.Farmer;
//import com.oracleous.extention_manager.data.model.Investor;
//import com.oracleous.extention_manager.data.model.UserPrincipal;
//import com.oracleous.extention_manager.data.model.Users;
//import com.oracleous.extention_manager.data.repositories.FarmersRepository;
//import com.oracleous.extention_manager.data.repositories.InvestorRepository;
//import com.oracleous.extention_manager.dto.response.investorFarmerRetrieval.InvestorViewFarmerResponse;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static com.oracleous.extention_manager.utilities.ApplicationUtilities.USER_NOT_FOUND;
//
//@Service
//@AllArgsConstructor
//@Slf4j
//public class InvestorViewAllFarmers implements InvestorViewFarmers {
//    private final InvestorRepository investorRepository;
//    private final FarmersRepository farmerRepository;
//
//    @Override
//    public List<InvestorViewFarmerResponse> getAllFarmers() {
//        Users user = getUsers();
//
//        Optional<Investor> investor = investorRepository.findByUsers(user);
//        if(investor.isEmpty()) {
//            throw new IllegalStateException(USER_NOT_FOUND);
//        }
//        log.info("Investor: {}", investor.get());
//
//        List<Farmer> farmers = farmerRepository.findAll();
//        if (farmers.isEmpty()) {
//            log.warn("No farmers found");
//            throw new IllegalStateException("No farmers available");
//        }
//        log.info("Found {} farmers", farmers.size());
//
//        return farmers.stream()
//                .map(farmer -> InvestorViewFarmerResponse.builder()
//                        .firstName(farmer.getFirstName())
//                        .lastName(farmer.getLastName())
//                        .residentialAddress(farmer.getResidentialAddress())
//                        .picture(farmer.getPassportPhotograph())
//                        .shortDescription(farmer.getDescription())
//                        .maritalStatus(farmer.getMaritalStatus() != null ? farmer.getMaritalStatus().name() : null)
//                        .build())
//                .collect(Collectors.toList());
//    }
//
//    private static @NotNull Users getUsers() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || !authentication.isAuthenticated()) {
//            throw new IllegalStateException("No authenticated user found");
//        }
//        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
//        Users users = userPrincipal.users();
//        if (users == null) {
//            throw new IllegalArgumentException(USER_NOT_FOUND);
//        }
//        return users;
//    }
//}
package com.oracleous.extention_manager.services.investorServices.investorViewFarmers;

import com.oracleous.extention_manager.data.model.AgriBusiness;
import com.oracleous.extention_manager.data.model.Farmer;
import com.oracleous.extention_manager.data.model.Investor;
import com.oracleous.extention_manager.data.model.UserPrincipal;
import com.oracleous.extention_manager.data.model.Users;
import com.oracleous.extention_manager.data.repositories.FarmersRepository;
import com.oracleous.extention_manager.data.repositories.InvestorRepository;
import com.oracleous.extention_manager.dto.response.investorAgriBusinessResponse.InvestorViewAgriBusinessResponse;
import com.oracleous.extention_manager.dto.response.investorFarmerRetrieval.InvestorViewFarmerResponse;
import com.oracleous.extention_manager.exceptions.InvestorNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.jetbrains.annotations.NotNull

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.oracleous.extention_manager.utilities.ApplicationUtilities.*;

@Service
@AllArgsConstructor
@Slf4j
public class InvestorViewAllFarmers implements InvestorViewFarmers {
    private final InvestorRepository investorRepository;
    private final FarmersRepository farmerRepository;

    @Override
    public List<InvestorViewFarmerResponse> getAllFarmers() {
        Users user = getUsers();
        Optional<Investor> investor = investorRepository.findByUsers(user);
        if (investor.isEmpty()) {throw new InvestorNotFoundException(INVESTOR_NOT_FOUND);}
        List<Farmer> farmers = farmerRepository.findAll();
        if (farmers.isEmpty()) {throw new IllegalStateException(FARMER_NOT_FOUND);}
        return farmers.stream()
            .map(farmer -> InvestorViewFarmerResponse.builder()
                .firstName(farmer.getFirstName())
                .lastName(farmer.getLastName())
                .residentialAddress(farmer.getResidentialAddress())
                .picture(farmer.getPassportPhotograph())
                .shortDescription(farmer.getDescription())
                .maritalStatus(farmer.getMaritalStatus() != null ? farmer.getMaritalStatus().name() : null)
                .build())
            .collect(Collectors.toList());
    }

    private static @NotNull Users getUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {throw new IllegalStateException(NO_AUTHENTICATION_USER_FOUND);}
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            Users users = userPrincipal.users();
        if (users == null) {throw new IllegalArgumentException(USER_NOT_FOUND);}
        return users;
    }
}