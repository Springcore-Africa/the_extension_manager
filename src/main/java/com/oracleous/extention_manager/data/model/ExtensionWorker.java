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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Extension Worker")

public class ExtensionWorker {
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private Users users ;

    @Column(name = "SHORT BIO")
    private String shortBio;
    @Column(name = "PHOTOGRAPH")
    private String passportPhotograph;

    @Enumerated (EnumType.STRING)
    private Stamp stamp = Stamp.PENDING;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;
    @PrePersist
    public void setCreatedAt(){
        createdAt = LocalDateTime.now();
    }
}
