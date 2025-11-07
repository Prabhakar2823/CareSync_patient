package com.medtech.patientportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/vitals")
public class VitalSignsController {

    @GetMapping("/{vitalType}")
    public String showVitalDetail(@PathVariable String vitalType, Model model) {
        // Set common attributes
        model.addAttribute("vitalType", vitalType);
        model.addAttribute("pageTitle", getVitalTitle(vitalType));

        // Set vital-specific data
        setVitalSpecificData(vitalType, model);

        return "vital-detail";
    }

    private String getVitalTitle(String vitalType) {
        switch (vitalType) {
            case "heart-rate": return "Heart Rate Monitoring";
            case "blood-pressure": return "Blood Pressure Tracking";
            case "oxygen": return "Oxygen Saturation Levels";
            case "temperature": return "Body Temperature Monitoring";
            case "glucose": return "Blood Glucose Levels";
            case "respiratory": return "Respiratory Rate Tracking";
            default: return "Vital Signs";
        }
    }

    private void setVitalSpecificData(String vitalType, Model model) {
        switch (vitalType) {
            case "heart-rate":
                model.addAttribute("currentValue", "72");
                model.addAttribute("unit", "BPM");
                model.addAttribute("normalRange", "60-100 BPM");
                model.addAttribute("status", "normal");
                model.addAttribute("statusText", "Normal");
                model.addAttribute("icon", "heart");
                model.addAttribute("description", "Heart rate measures the number of heartbeats per minute. A normal resting heart rate for adults ranges from 60 to 100 beats per minute.");
                break;

            case "blood-pressure":
                model.addAttribute("currentValue", "135/85");
                model.addAttribute("unit", "mmHg");
                model.addAttribute("normalRange", "120/80 mmHg");
                model.addAttribute("status", "warning");
                model.addAttribute("statusText", "Elevated");
                model.addAttribute("icon", "tachometer-alt");
                model.addAttribute("description", "Blood pressure measures the force of blood against artery walls. It's recorded as two numbers: systolic (pressure when heart beats) over diastolic (pressure when heart rests).");
                break;

            case "oxygen":
                model.addAttribute("currentValue", "98");
                model.addAttribute("unit", "% SpO₂");
                model.addAttribute("normalRange", "95-100%");
                model.addAttribute("status", "normal");
                model.addAttribute("statusText", "Optimal");
                model.addAttribute("icon", "lungs");
                model.addAttribute("description", "Oxygen saturation measures the percentage of oxygen-bound hemoglobin in the blood. Healthy levels are typically 95% or higher.");
                break;

            case "temperature":
                model.addAttribute("currentValue", "98.6");
                model.addAttribute("unit", "°F");
                model.addAttribute("normalRange", "97.8-99.1°F");
                model.addAttribute("status", "normal");
                model.addAttribute("statusText", "Normal");
                model.addAttribute("icon", "thermometer-half");
                model.addAttribute("description", "Body temperature measures the body's ability to generate and get rid of heat. The average normal body temperature is 98.6°F (37°C).");
                break;

            case "glucose":
                model.addAttribute("currentValue", "180");
                model.addAttribute("unit", "mg/dL");
                model.addAttribute("normalRange", "70-130 mg/dL");
                model.addAttribute("status", "critical");
                model.addAttribute("statusText", "High");
                model.addAttribute("icon", "vial");
                model.addAttribute("description", "Blood glucose measures the amount of sugar in your blood. Maintaining stable glucose levels is crucial for overall health.");
                break;

            case "respiratory":
                model.addAttribute("currentValue", "16");
                model.addAttribute("unit", "BPM");
                model.addAttribute("normalRange", "12-20 BPM");
                model.addAttribute("status", "normal");
                model.addAttribute("statusText", "Normal");
                model.addAttribute("icon", "wind");
                model.addAttribute("description", "Respiratory rate measures the number of breaths taken per minute. Normal rate for adults is 12 to 20 breaths per minute.");
                break;
        }

        // Add sample historical data
        model.addAttribute("historicalReadings", getSampleReadings(vitalType));
    }

    private List<Reading> getSampleReadings(String vitalType) {
        // Sample data for demonstration
        return Arrays.asList(
                new Reading(getValueForVital(vitalType, 1), LocalDateTime.now().minusHours(2), "After lunch"),
                new Reading(getValueForVital(vitalType, 2), LocalDateTime.now().minusHours(4), "Morning reading"),
                new Reading(getValueForVital(vitalType, 3), LocalDateTime.now().minusDays(1), "Evening check"),
                new Reading(getValueForVital(vitalType, 4), LocalDateTime.now().minusDays(2), "Routine monitoring"),
                new Reading(getValueForVital(vitalType, 5), LocalDateTime.now().minusDays(3), "Follow-up")
        );
    }

    private String getValueForVital(String vitalType, int index) {
        switch (vitalType) {
            case "heart-rate": return String.valueOf(70 + index * 2);
            case "blood-pressure": return (130 + index * 2) + "/" + (80 + index);
            case "oxygen": return String.valueOf(96 + index);
            case "temperature": return String.format("%.1f", 98.4 + index * 0.1);
            case "glucose": return String.valueOf(160 + index * 5);
            case "respiratory": return String.valueOf(15 + index);
            default: return "N/A";
        }
    }

    // Inner class for reading data
    public static class Reading {
        private String value;
        private LocalDateTime timestamp;
        private String notes;

        public Reading(String value, LocalDateTime timestamp, String notes) {
            this.value = value;
            this.timestamp = timestamp;
            this.notes = notes;
        }

        // Getters and setters
        public String getValue() { return value; }
        public LocalDateTime getTimestamp() { return timestamp; }
        public String getNotes() { return notes; }
    }
}