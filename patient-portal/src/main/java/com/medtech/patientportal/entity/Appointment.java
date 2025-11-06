package com.medtech.patientportal.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String doctorName;
    private String department;
    private LocalDateTime appointmentDate;
    private String status; // SCHEDULED, COMPLETED, CANCELLED
    private String reason;
    private String notes;

    private LocalDateTime createdAt = LocalDateTime.now();
}