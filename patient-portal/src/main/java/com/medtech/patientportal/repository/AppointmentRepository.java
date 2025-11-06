package com.medtech.patientportal.repository;

import com.medtech.patientportal.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientIdOrderByAppointmentDateDesc(Long patientId);
    List<Appointment> findByPatientIdAndStatusOrderByAppointmentDateAsc(Long patientId, String status);
}