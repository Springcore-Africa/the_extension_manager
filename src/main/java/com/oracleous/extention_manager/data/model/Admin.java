package com.oracleous.extention_manager.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(unique = true, nullable = false)
//    private String email;
//    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private Users users ;

    private String name;
    private boolean confirmed;
}