package com.practice.hospital_management.service;

import com.practice.hospital_management.models.Patient;
import com.practice.hospital_management.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        try {
            return patientRepository.findAll();
        } catch (Exception e) {
            logger.error("error occurred while fetching all patient: {}", e.getMessage());
            throw e;
        }
    }

    public Optional<Patient> getPatientById(Long id) {
        try {
            return patientRepository.findById(id);
        } catch (Exception e) {
            logger.error("error occurred while fetching patient with id: {} {}", id, e.getMessage());
            return Optional.empty();
        }
    }

    public Patient createPatient(Patient patient) {
        try {
            return patientRepository.save(patient);
        } catch (Exception e) {
            logger.error("error occurred while creating patient with id: {} {}", patient.getId(), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Patient updatePatient(Long id, Patient patientDetails) {
        try {
            Optional<Patient> optionalPatient = patientRepository.findById(id);
            if (optionalPatient.isPresent()) {
                Patient patient = optionalPatient.get();
                patient.setName(patientDetails.getName());
                patient.setAge(patientDetails.getAge());
                patient.setGender(patientDetails.getGender());
                return patientRepository.save(patient);
            } else {
                logger.warn("patient with id {} not found", id);
                return null;
            }
        } catch (Exception e) {
            logger.error("error occurred while creating patient with id: {} {}", id, e.getMessage());
            throw e;
        }

    }

    public boolean deletePatient(Long id) {
        try {
            if (patientRepository.existsById(id)) {
                patientRepository.deleteById(id);
                return true;
            } else {
                logger.warn("patient with id {} not found", id);
                return false;
            }
        } catch (Exception e) {
            logger.error("error occurred while deleting patient with id: {} {}", id, e.getMessage());
            throw e;
        }
    }
}




















