package com.oracleous.extention_manager.data.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Agri_Business")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgriBusiness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "BUSINESS NAME")
    private String businessName;
    @Column(name = "LOCATION (STATE)")
    private String businessLocationState;
    @Column(name = "LOCATION (LGA)")
    private String businessLocationLga;
    @Column(name = "EXACT LOCATION")
    private String businessLocationExact;
    @Column(name = "CAC NUMBER")
    private String cacNumber;
    @Column(name = "CAC REG DATE")
    private LocalDateTime cacRegistrationDate;
    @Column(name = "REGISTRATION NUMBER")
    private String regNumber;
    @Column(name = "FARM SIZE")
    private int farmSize;
    @Column(name = "TYPE OF PRODUCT")
    private String agriculturalProduct;
    @Column(name = "YEARLY PRODUCTION")
    private int yearlyProduction;
    @Column(name = "NUMBER OF EMPLOYEES")
    private int numberOfEmployees;
    @Column(name = "PRODUCT DESCRIPTION")
    private String productDescription;
    @Column(name = "CAC CERTIFICATE")
    private String cacCertificate;
    @Column(name = "MEMART")
    private String memart;
    @Column(name = "FARM PHOTOS")
    private String farmPhotos;
    @Column(name = "PRODUCT PHOTOS")
    private String productPhotos;
    @OneToOne(mappedBy = "agriBusiness")
    private Farmer farmer;


    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;
    @PrePersist
    public void setCreatedAt(){
        createdAt = LocalDateTime.now();
    }
}
