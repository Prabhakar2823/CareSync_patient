package com.medtech.patientportal.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard(Authentication authentication, Model model) {
        model.addAttribute("upcomingAppointments", 3);
        model.addAttribute("recentRecords", 12);
        model.addAttribute("pendingPayments", 2);
        model.addAttribute("welcomeMessage", "Welcome to your Health Dashboard");

        if (authentication != null && authentication.getName() != null) {
            String username = authentication.getName();
            model.addAttribute("username", username);
        }

        return "dashboard";
    }

    @GetMapping("/appointments")
    public String showAppointments(Authentication authentication, Model model) {
        if (authentication != null && authentication.getName() != null) {
            String username = authentication.getName();
            model.addAttribute("username", username);
        }
        return "appointments";
    }

    @GetMapping("/medical-records")
    public String showMedicalRecords(Authentication authentication, Model model) {
        if (authentication != null && authentication.getName() != null) {
            String username = authentication.getName();
            model.addAttribute("username", username);
        }
        return "medical-records";
    }

    @GetMapping("/prescriptions")
    public String showPrescriptions(Authentication authentication, Model model) {
        if (authentication != null && authentication.getName() != null) {
            String username = authentication.getName();
            model.addAttribute("username", username);
        }
        return "prescriptions";
    }

    @GetMapping("/billing")
    public String showBilling(Authentication authentication, Model model) {
        if (authentication != null && authentication.getName() != null) {
            String username = authentication.getName();
            model.addAttribute("username", username);
        }
        return "billing";
    }

    @GetMapping("/profile")
    public String showProfile(Authentication authentication, Model model) {
        if (authentication != null && authentication.getName() != null) {
            String username = authentication.getName();
            model.addAttribute("username", username);
        }
        return "profile";
    }

    @GetMapping("/vitals")
    public String showVitalsOverview() {
        return "redirect:/dashboard#health-metrics";
    }
}