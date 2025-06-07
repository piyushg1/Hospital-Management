package com.practice.hospital_management.controllers;

import com.practice.hospital_management.models.Patient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    @GetMapping
    public List<Patient> getPatients() {
        System.out.println("getPatients");
        return null;
    }

    @PostMapping()
    public Patient addPatient(@RequestBody Patient patient){
        System.out.println("addPatient");
        return patient;
    }

    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable Long id){
        System.out.println("getPatient");
        return null;
    }
}
