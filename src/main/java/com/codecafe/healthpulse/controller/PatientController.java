package com.codecafe.healthpulse.controller;

import com.codecafe.healthpulse.dto.DashboardStats;
import com.codecafe.healthpulse.model.Patient;
import com.codecafe.healthpulse.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping("patients")
    public Patient save(@Valid @RequestBody Patient patient) {
        return patientService.create(patient);
    }

    @GetMapping("patients")
    public List<Patient> getAll() {
        return patientService.findAll();
    }

    @GetMapping("patients/{id}")
    public Patient get(@PathVariable String id) {
        return patientService.findById(id);
    }

    @PutMapping("patients/{id}")
    public Patient update(@PathVariable String id, @Valid @RequestBody Patient patient) {
        return patientService.update(id, patient);
    }

    @DeleteMapping("patients/{id}")
    public void deleteById(@PathVariable String id) {
        patientService.deleteById(id);
    }

    // GET http://localhost:9090/patients/department/Cardiology
    @GetMapping("patients/department/{department}")
    public List<Patient> getByDepartment(@PathVariable String department) {
        return patientService.findByDepartment(department);
    }

    // GET http://localhost:9090/patients/search?name=john
    @GetMapping("patients/search")
    public List<Patient> search(@RequestParam String name) {
        return patientService.searchByName(name);
    }

    // GET http://localhost:9090/patients/dashboard
    @GetMapping("patients/dashboard")
    public DashboardStats getDashboardStats() {
        return patientService.getDashboardStats();
    }
}
