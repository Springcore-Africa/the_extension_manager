package com.oracleous.extention_manager.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Farm {
    @Id
    private Long id ;

    private String farmName ;
    private String location ;
    private String farmSize ;
    private String cropPlanted ;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] farmPicture ;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FARMER_ID")
    private Farmer farmer ;
}
