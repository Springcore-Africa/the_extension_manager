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
@Table(name = "Farmer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Farmer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "FIRST NAME")
    private String firstName;
    @Column(name = "LAST NAME")
    private String lastName;
    @Column(name = "PHONE NUMBER")
    private String phoneNumber;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "NATIONAL ID")
    private String nationalId;
    @Column(name = "DATE OF BIRTH")
    private LocalDateTime dateOfBirth;
    @Column(name = "STATE OF ORIGIN")
    private String stateOfOrigin;
    @Column(name = "LGA OF ORIGIN")
    private String lgaOfOrigin;
    @Column(name = "ADDRESS")
    private String residentialAddress;
    @Column(name = "NUMBER OF CHILDREN")
    private int numberOfChildren;
    @Column(name = "REGISTRATION NUMBER")
    private String regNumber;
    @Column(name = "SHORT DESCRIPTION")
    private String description;
    @Column(name = "GENDER")
    private Enum<Gender> gender;
    @Column(name = "MARITAL STATUS")
    private Enum<MaritalStatus> maritalStatus;
    @Column(name = "NIN SLIP")
    private String ninSlip;
    @Column(name = "BIRTH CERTIFICATE")
    private String birthCertificate;
    @Column(name = "CERTIFICATE")
    private String lastEducationalCertificate;
    @Column(name = "PHOTOGRAPH")
    private String passportPhotograph;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BUSINESS_ID", referencedColumnName = "ID")
    private AgriBusiness agriBusiness;
    @Column(name = "RegistrationToken")
    private String verificationToken ;
    private boolean isVerified ;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;
    @PrePersist
    public void setCreatedAt(){
        createdAt = LocalDateTime.now();
    }


}
