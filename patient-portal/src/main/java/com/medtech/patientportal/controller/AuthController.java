package com.medtech.patientportal.controller;

import com.medtech.patientportal.dto.RegistrationDTO;
import com.medtech.patientportal.entity.User;
import com.medtech.patientportal.entity.Patient;
import com.medtech.patientportal.service.UserService;
import com.medtech.patientportal.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout,
                                @RequestParam(value = "success", required = false) String success,
                                @RequestParam(value = "reset", required = false) String reset,
                                Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid email/phone or password!");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }
        if (success != null) {
            model.addAttribute("success", "Registration successful! Please login with your credentials.");
        }
        if (reset != null) {
            model.addAttribute("success", "Password has been reset successfully! Please login with your new password.");
        }
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new RegistrationDTO());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") RegistrationDTO registrationDTO,
                                      Model model,
                                      RedirectAttributes redirectAttributes) {
        try {
            // Validate email existence
            if (userService.emailExists(registrationDTO.getEmail())) {
                model.addAttribute("error", "Email already exists! Please use a different email.");
                return "register";
            }

            // Validate password match
            if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
                model.addAttribute("error", "Passwords do not match!");
                return "register";
            }

            // Validate password length
            if (registrationDTO.getPassword().length() < 6) {
                model.addAttribute("error", "Password must be at least 6 characters long!");
                return "register";
            }

            // Create User
            User user = new User();
            user.setEmail(registrationDTO.getEmail());
            user.setPhoneNumber(registrationDTO.getPhoneNumber());
            user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
            user.setRole("PATIENT");
            User savedUser = userService.registerUser(user);

            // Create Patient
            Patient patient = new Patient();
            patient.setUser(savedUser);
            patient.setFirstName(registrationDTO.getFirstName());
            patient.setLastName(registrationDTO.getLastName());
            patient.setDateOfBirth(registrationDTO.getDateOfBirth());
            patient.setGender(registrationDTO.getGender());
            patient.setAddress(registrationDTO.getAddress());
            patient.setEmergencyContact(registrationDTO.getEmergencyContact());
            patient.setBloodGroup(registrationDTO.getBloodGroup());
            patient.setAllergies(registrationDTO.getAllergies());
            patientService.savePatient(patient);

            // Add success message and redirect to login
            redirectAttributes.addFlashAttribute("success", "Registration successful! Please login with your credentials.");
            return "redirect:/login?success=true";

        } catch (Exception e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "register";
        }
    }
}