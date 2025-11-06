package com.medtech.patientportal.repository;

import com.medtech.patientportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);

    // Add this method to fix the error
    @Query("SELECT u FROM User u WHERE u.email = :username OR u.phoneNumber = :username")
    Optional<User> findByEmailOrPhoneNumber(@Param("username") String username);
}