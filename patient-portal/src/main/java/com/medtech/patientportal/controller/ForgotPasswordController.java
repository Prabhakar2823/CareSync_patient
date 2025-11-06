package com.medtech.patientportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ForgotPasswordController {

    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam String email,
                                        RedirectAttributes redirectAttributes) {
        try {
            // TODO: Implement actual password reset logic
            // 1. Check if email exists in database
            // 2. Generate reset token
            // 3. Send email with reset link
            // 4. Store token in database with expiration

            // For now, we'll just show a success message
            System.out.println("Password reset requested for email: " + email);

            redirectAttributes.addFlashAttribute("success",
                    "Password reset instructions have been sent to your email!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Failed to process password reset request. Please try again.");
        }

        return "redirect:/login";
    }

    @GetMapping("/reset-password")
    public String showResetPasswordPage(@RequestParam String token, Model model) {
        // TODO: Validate token
        // 1. Check if token exists and is not expired
        // 2. If valid, show reset password form
        // 3. If invalid, show error message

        model.addAttribute("token", token);
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String processResetPassword(@RequestParam String token,
                                       @RequestParam String newPassword,
                                       @RequestParam String confirmPassword,
                                       RedirectAttributes redirectAttributes) {
        try {
            // TODO: Implement actual password reset logic
            // 1. Validate token
            // 2. Check if passwords match
            // 3. Update password in database
            // 4. Invalidate token

            if (!newPassword.equals(confirmPassword)) {
                redirectAttributes.addFlashAttribute("error", "Passwords do not match!");
                return "redirect:/reset-password?token=" + token;
            }

            // For demo purposes
            System.out.println("Password reset for token: " + token);

            redirectAttributes.addFlashAttribute("success",
                    "Password has been reset successfully! Please login with your new password.");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Failed to reset password. Please try again.");
        }

        return "redirect:/login";
    }
}