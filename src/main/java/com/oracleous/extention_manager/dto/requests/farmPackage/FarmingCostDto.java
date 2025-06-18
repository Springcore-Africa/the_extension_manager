package com.oracleous.extention_manager.dto.requests.farmPackage;

import com.oracleous.extention_manager.data.model.CostItem;
import com.oracleous.extention_manager.data.model.CostItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmingCostDto {
    private Map<CostItemType, CostItem> costItems = new EnumMap<>(CostItemType.class);
    private BigDecimal totalDirectCost;
    private BigDecimal totalOverheadCost;
    private BigDecimal totalFarmingCost;

}
