package com.medtech.patientportal.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RegistrationDTO {
    private String email;
    private String phoneNumber;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String emergencyContact;
    private String bloodGroup;
    private String allergies;
}