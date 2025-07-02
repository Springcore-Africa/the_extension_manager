package com.oracleous.extention_manager.dto.requests.farmPackage;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CostItemDto {
    private BigDecimal price;
    private BigDecimal quantity;
}
