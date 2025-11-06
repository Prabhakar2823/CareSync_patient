package com.medtech.patientportal.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    private String role; // PATIENT, DOCTOR, ADMIN

    private boolean enabled = true;

    private LocalDateTime createdAt = LocalDateTime.now();
}