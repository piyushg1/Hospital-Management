package com.practice.hospital_management.controllers;

import com.practice.hospital_management.models.Bill;
import com.practice.hospital_management.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/bills")
public class BIllController {
    @Autowired
    private BillService billService;

    @GetMapping
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    @GetMapping("/{id}")
    public Optional<Bill> getBillById(@PathVariable Long id) {
        return billService.getBillById(id);
    }

    @PostMapping
    public Bill creteBill(@RequestBody Bill bill) {
        return billService.createBill(bill);
    }

    @PutMapping("/{id}")
    public Bill updateBill(@RequestBody Bill bill, @PathVariable Long id) {
        return billService.updateBill(id, bill);
    }

    @DeleteMapping("/{id}")
    public boolean deleteBill(@PathVariable Long id) {
        return billService.deleteBill(id);
    }
}
