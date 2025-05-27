package com.oracleous.extention_manager.data.repositories;

import com.oracleous.extention_manager.data.model.Admin;
import com.oracleous.extention_manager.data.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByUsersEmail(String email);

    boolean existsByUsersEmail(String email);

    Admin findByUsers(Users users);
}
