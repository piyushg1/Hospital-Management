package com.practice.hospital_management.controllers;

import com.practice.hospital_management.models.Patient;
import com.practice.hospital_management.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping
    public List<Patient> getPatients() {
        return patientService.getAllPatients();
    }

    @PostMapping()
    public Patient addPatient(@RequestBody Patient patient) {
        patientService.createPatient(patient);
        return patient;
    }

    @GetMapping("/{id}")
    public Optional<Patient> getPatient(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        return patientService.updatePatient(id, patient);
    }

    @DeleteMapping("/{id}")
    public boolean deletePatient(@PathVariable Long id) {
        return patientService.deletePatient(id);
    }
}
