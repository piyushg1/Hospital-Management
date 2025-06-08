package com.practice.hospital_management.service;

import com.practice.hospital_management.models.Appointment;
import com.practice.hospital_management.repository.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);
    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        try {
            return appointmentRepository.findAll();
        } catch (Exception e) {
            logger.error("error occurred while fetching all appointment: {}", e.getMessage());
            throw e;
        }
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        try {
            return appointmentRepository.findById(id);
        } catch (Exception e) {
            logger.error("error occurred while fetching Appointment with id: {} {}", id, e.getMessage());
            return Optional.empty();
        }
    }

    public Appointment createAppointment(Appointment appointment) {
        try {
            return appointmentRepository.save(appointment);
        } catch (Exception e) {
            logger.error("error occurred while creating appointment with id: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        try {
            Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
            if (optionalAppointment.isPresent()) {
                Appointment appointment = optionalAppointment.get();
                appointment.setAppointmentId(appointmentDetails.getAppointmentId());
                appointment.setDate(appointmentDetails.getDate());
                appointment.setDoctorId(appointmentDetails.getDoctorId());
                return appointmentRepository.save(appointment);
            } else {
                logger.warn("appointment with id {} not found", id);
                return null;
            }
        } catch (Exception e) {
            logger.error("error occurred while creating appointment with id: {} {}", id, e.getMessage());
            throw e;
        }

    }

    public boolean deleteAppointment(Long id) {
        try {
            if (appointmentRepository.existsById(id)) {
                appointmentRepository.deleteById(id);
                return true;
            } else {
                logger.warn("appointment with id {} not found", id);
                return false;
            }
        } catch (Exception e) {
            logger.error("error occurred while deleting appointment with id: {} {}", id, e.getMessage());
            throw e;
        }
    }
}
