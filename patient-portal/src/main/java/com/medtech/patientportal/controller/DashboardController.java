package com.medtech.patientportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // Add dashboard data
        model.addAttribute("upcomingAppointments", 3);
        model.addAttribute("recentRecords", 5);
        model.addAttribute("pendingPayments", 2);
        model.addAttribute("welcomeMessage", "Welcome to your Patient Dashboard");
        return "dashboard";
    }
}