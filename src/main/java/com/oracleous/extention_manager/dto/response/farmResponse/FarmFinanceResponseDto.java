package com.oracleous.extention_manager.dto.response.farmResponse;

import com.oracleous.extention_manager.data.model.FarmingCost;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FarmFinanceResponseDto {

    private BigDecimal totalQuantityOfCropProduced;
    private BigDecimal totalQuantitySold;
    private BigDecimal pricePerQuantity;
    private BigDecimal totalDirectCost;

    private BigDecimal totalRevenue;
    private BigDecimal grossProfit;
    private BigDecimal netProfit;

    private BigDecimal overHeadCost;

    private FarmingCost farmingCost ;

}
