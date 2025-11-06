package com.medtech.patientportal.service;

import com.medtech.patientportal.entity.Patient;
import com.medtech.patientportal.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Optional<Patient> findByUserEmail(String email) {
        return patientRepository.findByUserEmail(email);
    }
}