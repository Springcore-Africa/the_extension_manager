package com.oracleous.extention_manager.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmFinance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String TotalQuantityOfCropProduced;
    private String TotalQuantitySold ;
    private String PricePerQuantity  ;
    private String TotalRevenue ;
    private String TotalDirectCost  ;
    private String GrossProfit ;
    private String OverHeadCost ;
    private String NetProfit ;
}
