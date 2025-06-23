package com.oracleous.extention_manager.services.farmService;

import com.oracleous.extention_manager.data.model.FarmFinance;
import com.oracleous.extention_manager.data.model.FarmingCost;
import com.oracleous.extention_manager.data.repositories.FarmFinanceRepository;
import com.oracleous.extention_manager.data.repositories.FarmingCostRepository;
import com.oracleous.extention_manager.dto.requests.farmPackage.FarmFinanceRequestDto;
import com.oracleous.extention_manager.dto.response.farmResponse.FarmFinanceResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
@AllArgsConstructor
public class FarmFinanceServiceImpl implements FarmFinanceService{
    private final FarmFinanceRepository farmFinanceRepository;
    private final FarmingCostRepository farmingCostRepository;

    @Override
    public FarmFinanceResponseDto calculateFarmFinance(FarmFinanceRequestDto farmFinanceRequestDto) {
        FarmingCost farmingCost = farmingCostRepository.fingById(farmFinanceRequestDto.getFarmingCostId()).orElseThrow(()-> new IllegalArgumentException("Farming not found"));

        BigDecimal totalRevenue = farmFinanceRequestDto.getPricePerQuantity()
                .multiply(farmFinanceRequestDto.getTotalQuantitySold());
        BigDecimal grossProfit = totalRevenue.subtract(farmFinanceRequestDto.getTotalQuantitySold());
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