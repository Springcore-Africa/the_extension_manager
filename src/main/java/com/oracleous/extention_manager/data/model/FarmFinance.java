package com.oracleous.extention_manager.data.model;

import com.oracleous.extention_manager.data.model.FarmingCost;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmFinance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalQuantityOfCropProduced;
    private BigDecimal totalQuantitySold;
    private BigDecimal pricePerQuantity;
    private BigDecimal totalRevenue;
    private BigDecimal totalDirectCost;
    private BigDecimal grossProfit;
    private BigDecimal overHeadCost;
    private BigDecimal netProfit;

    @OneToOne
    private FarmingCost farmingCost;
}
