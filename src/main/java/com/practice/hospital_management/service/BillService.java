package com.practice.hospital_management.service;

import com.practice.hospital_management.models.Bill;
import com.practice.hospital_management.repository.BillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    private static final Logger logger = LoggerFactory.getLogger(BillService.class);
    @Autowired
    private BillRepository billRepository;

    public List<Bill> getAllBills() {
        try {
            return billRepository.findAll();
        } catch (Exception e) {
            logger.error("error occurred while fetching all bill: {}", e.getMessage());
            throw e;
        }
    }

    public Optional<Bill> getBillById(Long id) {
        try {
            return billRepository.findById(id);
        } catch (Exception e) {
            logger.error("error occurred while fetching Bill with id: {} {}", id, e.getMessage());
            return Optional.empty();
        }
    }

    public Bill createBill(Bill bill) {
        try {
            return billRepository.save(bill);
        } catch (Exception e) {
            logger.error("error occurred while creating bill with id: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Bill updateBill(Long id, Bill billDetails) {
        try {
            Optional<Bill> optionalBill = billRepository.findById(id);
            if (optionalBill.isPresent()) {
                Bill bill = optionalBill.get();
                bill.setStatus(billDetails.getStatus());
                bill.setAmount(billDetails.getAmount());
                bill.setPatientId(billDetails.getPatientId());
                return billRepository.save(bill);
            } else {
                logger.warn("bill with id {} not found", id);
                return null;
            }
        } catch (Exception e) {
            logger.error("error occurred while creating bill with id: {} {}", id, e.getMessage());
            throw e;
        }

    }

    public boolean deleteBill(Long id) {
        try {
            if (billRepository.existsById(id)) {
                billRepository.deleteById(id);
                return true;
            } else {
                logger.warn("bill with id {} not found", id);
                return false;
            }
        } catch (Exception e) {
            logger.error("error occurred while deleting bill with id: {} {}", id, e.getMessage());
            throw e;
        }
    }
}
