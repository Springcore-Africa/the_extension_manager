package com.oracleous.extention_manager.data.repositories;

import com.oracleous.extention_manager.data.model.AgriBusiness;
import com.oracleous.extention_manager.data.model.Farmer;
import com.oracleous.extention_manager.data.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FarmersRepository extends JpaRepository<Farmer, Long> {

@Query("SELECT CASE WHEN COUNT(f) > 0 THEN TRUE ELSE FALSE END FROM Farmer f WHERE LOWER(f.users.email) = LOWER(:email) OR f.nationalId = :nationalId OR f.phoneNumber = :phoneNumber")
boolean existsByEmailOrNationalIdOrPhoneNumber(@Param("email") String email,
                                               @Param("nationalId") String nationalId,
                                               @Param("phoneNumber") String phoneNumber);

@Query("SELECT f FROM Farmer f WHERE " +
        "(:email IS NOT NULL AND LOWER(f.users.email) = LOWER(:email)) OR " +
        "(:phoneNumber IS NOT NULL AND f.phoneNumber = :phoneNumber)")
Optional<Farmer> findByEmailOrPhoneNumber(@Param("email") String email, @Param("phoneNumber") String phoneNumber);

    Optional<Farmer> findByUsers(Users users);

    AgriBusiness findByAgriBusiness(AgriBusiness agriBusiness);
}
