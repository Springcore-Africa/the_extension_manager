package com.oracleous.extention_manager.dto.requests.farmPackage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FarmFinanceRequestDto {
    private BigDecimal totalQuantityOfCropProduced;
    private BigDecimal totalQuantitySold;
    private BigDecimal pricePerQuantity;
    private BigDecimal overHeadCost;

    private Long farmingCostId;
}
