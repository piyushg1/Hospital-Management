package com.practice.hospital_management.service;

import com.practice.hospital_management.models.Doctor;
import com.practice.hospital_management.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        try {
            return doctorRepository.findAll();
        } catch (Exception e) {
            logger.error("error occurred while fetching all doctor: {}", e.getMessage());
            throw e;
        }
    }

    public Optional<Doctor> getDoctorById(Long id) {
        try {
            return doctorRepository.findById(id);
        } catch (Exception e) {
            logger.error("error occurred while fetching doctor with id: {} {}", id, e.getMessage());
            return Optional.empty();
        }
    }

    public Doctor createDoctor(Doctor doctor) {
        try {
            return doctorRepository.save(doctor);
        } catch (Exception e) {
            logger.error("error occurred while creating doctor with id: {} {}", doctor.getId(), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Doctor updateDoctor(Long id, Doctor doctorDetails) {
        try {
            Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
            if (optionalDoctor.isPresent()) {
                Doctor doctor = optionalDoctor.get();
                doctor.setName(doctorDetails.getName());
                doctor.setAge(doctorDetails.getAge());
                doctor.setSpecialty(doctorDetails.getSpecialty());
                return doctorRepository.save(doctor);
            } else {
                logger.warn("doctor with id {} not found", id);
                return null;
            }
        } catch (Exception e) {
            logger.error("error occurred while creating doctor with id: {} {}", id, e.getMessage());
            throw e;
        }

    }

    public boolean deleteDoctor(Long id) {
        try {
            if (doctorRepository.existsById(id)) {
                doctorRepository.deleteById(id);
                return true;
            } else {
                logger.warn("doctor with id {} not found", id);
                return false;
            }
        } catch (Exception e) {
            logger.error("error occurred while deleting doctor with id: {} {}", id, e.getMessage());
            throw e;
        }
    }
}
