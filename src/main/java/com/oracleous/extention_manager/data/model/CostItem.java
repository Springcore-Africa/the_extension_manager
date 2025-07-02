package com.oracleous.extention_manager.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CostItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Getters and setters
    @Getter
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal totalAmount;

}
