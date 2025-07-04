package com.oracleous.extention_manager.services.farmService;

import com.oracleous.extention_manager.data.model.FarmFinance;
import com.oracleous.extention_manager.data.model.FarmingCost;
import com.oracleous.extention_manager.data.model.UserPrincipal;
import com.oracleous.extention_manager.data.model.Users;
import com.oracleous.extention_manager.data.repositories.FarmFinanceRepository;
import com.oracleous.extention_manager.data.repositories.FarmingCostRepository;
import com.oracleous.extention_manager.dto.requests.farmPackage.FarmFinanceRequestDto;
import com.oracleous.extention_manager.dto.response.farmResponse.FarmFinanceResponseDto;
import com.oracleous.extention_manager.utilities.ApplicationUtilities;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
@AllArgsConstructor
@Slf4j
public class FarmFinanceServiceImpl implements FarmFinanceService{
    private final FarmFinanceRepository farmFinanceRepository;
    private final FarmingCostRepository farmingCostRepository;

    @Override
    public FarmFinanceResponseDto calculateFarmFinance(FarmFinanceRequestDto farmFinanceRequestDto) {
     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("this is authentication {}", authentication);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Users user = userPrincipal.users();
        log.info("this is user {}", user);

        FarmingCost farmingCost = farmingCostRepository.findTopByUserOrderByIdDesc(user)
                .orElseThrow(() -> new IllegalArgumentException("No farming cost record found for user"));
        log.info("this is the farming cost {}", farmingCost);

//        Users currentUser = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).users();
//        FarmingCost farmingCost = farmingCostRepository.findTopByUserOrderByIdDesc(currentUser).orElseThrow(()-> new IllegalArgumentException("Farming not found"));

        BigDecimal totalRevenue = farmFinanceRequestDto.getPricePerQuantity().multiply(farmFinanceRequestDto.getTotalQuantitySold());
        BigDecimal grossProfit = totalRevenue.subtract(farmingCost.getTotalDirectCost());
        BigDecimal netProfit = grossProfit.subtract(farmFinanceRequestDto.getOverHeadCost());
        FarmFinance savedFinance = farmFinanceRepository.save(
            FarmFinance.builder()
                .totalQuantityOfCropProduced(farmFinanceRequestDto.getTotalQuantityOfCropProduced())
                .totalQuantitySold(farmFinanceRequestDto.getTotalQuantitySold())
                .pricePerQuantity(farmFinanceRequestDto.getPricePerQuantity())
                .totalRevenue(totalRevenue)
                .totalDirectCost(farmingCost.getTotalDirectCost())
                .grossProfit(grossProfit)
                .overHeadCost(farmFinanceRequestDto.getOverHeadCost())
                .netProfit(netProfit)
                .farmingCost(farmingCost)
                .build()
        );
        return FarmFinanceResponseDto.builder()
                .pricePerQuantity(farmFinanceRequestDto.getPricePerQuantity())
                .totalQuantitySold(farmFinanceRequestDto.getTotalQuantitySold())
                .overHeadCost(farmFinanceRequestDto.getOverHeadCost())
                .totalQuantityOfCropProduced(farmFinanceRequestDto.getTotalQuantityOfCropProduced())
                .totalRevenue(totalRevenue)
                .grossProfit(grossProfit)
                .netProfit(netProfit)
                .totalDirectCost(farmingCost.getTotalDirectCost())
                .farmingCost(farmingCost)
                .build();
    }


}