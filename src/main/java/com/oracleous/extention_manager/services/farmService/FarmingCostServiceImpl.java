package com.oracleous.extention_manager.services.farmService;

import com.oracleous.extention_manager.data.model.CostItemType;
import com.oracleous.extention_manager.data.model.FarmingCost;
import com.oracleous.extention_manager.data.model.UserPrincipal;
import com.oracleous.extention_manager.data.model.Users;
import com.oracleous.extention_manager.data.repositories.FarmingCostRepository;
import com.oracleous.extention_manager.dto.requests.farmPackage.CostItemDto;
import com.oracleous.extention_manager.dto.requests.farmPackage.FarmingCostDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
@AllArgsConstructor
public class FarmingCostServiceImpl implements FarmingCostService {
    private final FarmingCostRepository costRepository;
    private final FarmingCostRepository farmingCostRepository;

    @Override
    public FarmingCostDto calculateCosts(FarmingCostDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()) {throw new IllegalArgumentException("Authentication required");}
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        if(userPrincipal == null) {throw new IllegalArgumentException("User not found");}

        Users user = userPrincipal.users();
        BigDecimal totalDirect = sum(dto, CostItemType.SEED, CostItemType.LABOR);
        BigDecimal totalOverhead = sum(dto,
                CostItemType.FERTILIZER,
                CostItemType.HERBICIDE,
                CostItemType.PESTICIDE,
                CostItemType.OTHER_ONE,
                CostItemType.OTHER_TWO,
                CostItemType.OTHER_THREE
        );
        BigDecimal totalFarming = totalDirect.add(totalOverhead);

        FarmingCost saved = farmingCostRepository.save(FarmingCost.builder()
                .totalDirectCost(totalDirect)
                .totalOverheadCost(totalOverhead)
                .totalFarmingCost(totalFarming)
                .user(user)
                .build()
        );
        dto.setTotalDirectCost(totalDirect);
        dto.setTotalOverheadCost(totalOverhead);
        dto.setTotalFarmingCost(totalDirect.add(totalOverhead));
        return dto;

    }
    private BigDecimal sum(FarmingCostDto dto, CostItemType... types) {
        return Arrays.stream(types)
                .map(type -> {
                    CostItemDto item = dto.getCostItems().get(type);
                    if (item == null) return BigDecimal.ZERO;

                    BigDecimal price = item.getPrice() != null ? item.getPrice() : BigDecimal.ZERO;
                    BigDecimal quantity = item.getQuantity() != null ? item.getQuantity() : BigDecimal.ZERO;
                    return price.multiply(quantity);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
