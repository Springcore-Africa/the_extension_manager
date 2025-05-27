package com.oracleous.extention_manager.data.repositories;

import com.oracleous.extention_manager.data.model.Investor;
import com.oracleous.extention_manager.data.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvestorRepository extends JpaRepository<Investor, Long> {

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM Investor i WHERE i.users.email = :email AND i.phoneNumber = :phoneNumber")
    boolean existsByUsersEmailAndPhoneNumber(@Param("email") String email, @Param("phoneNumber") String phoneNumber);

    Optional<Investor> findByUsersEmailOrPhoneNumber(String email, String phoneNumber);

    Optional<Investor> findByUsers(Users users);
}
