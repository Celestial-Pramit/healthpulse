package com.codecafe.healthpulse.service;

import com.codecafe.healthpulse.dto.DashboardStats;
import com.codecafe.healthpulse.model.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> findAll();

    Patient findById(String id);

    Patient create(Patient patient);

    Patient update(String id, Patient patient);

    void deleteById(String id);

    List<Patient> findByDepartment(String department);

    List<Patient> searchByName(String name);

    DashboardStats getDashboardStats();
}
