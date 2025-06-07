package com.practice.hospital_management.service;

import com.practice.hospital_management.models.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    public List<Patient> getAllPatient() {
        try {
            System.out.println("in service layer");
            return null;
        } catch (Exception e) {
            logger.error("error occurred while fetching all patient: {}", e.getMessage());
            return null;
        }
    }
}
