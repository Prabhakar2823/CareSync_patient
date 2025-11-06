package com.medtech.patientportal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

@RestController
public class VitalsController {

    private final Random rnd = new Random();

    @GetMapping("/api/vitals")
    public Map<String, Object> getVitals() {
        // Simulate values for demo - replace with real data source
        int heartRate = 60 + rnd.nextInt(40); // 60 - 99
        int bpSystolic = 100 + rnd.nextInt(30); // 100 - 129
        int bpDiastolic = 60 + rnd.nextInt(20); // 60 - 79
        int spO2 = 94 + rnd.nextInt(6); // 94 - 99
        double temperature = 36.0 + rnd.nextDouble() * 1.8; // 36.0 - 37.8

        return Map.of(
                "heartRate", heartRate,
                "bpSystolic", bpSystolic,
                "bpDiastolic", bpDiastolic,
                "spO2", spO2,
                "temperature", Math.round(temperature * 10.0) / 10.0
        );
    }
}
