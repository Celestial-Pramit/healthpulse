package com.codecafe.healthpulse;

import com.codecafe.healthpulse.enums.Acuity;
import com.codecafe.healthpulse.model.Patient;
import com.codecafe.healthpulse.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PatientTest {

    @Autowired
    private PatientService patientService;

    // Unique per run so the "MRN already exists" business rule never clashes
    private static String uniqueMrn(String prefix) {
        return prefix + "-" + UUID.randomUUID();
    }

    @Test
    public void testSavePatient() {
        Patient patient = Patient.builder()
                .mrn(uniqueMrn("MRN"))
                .patientName("Jonathan Miller")
                .department("Cardiology")
                .assignedBed("BED-C-102")
                .acuity(Acuity.CRITICAL)
                .build();
        Patient saved = patientService.create(patient);
        assertNotNull(saved.getId());
    }

    @Test
    public void testFindAllPatients() {
        List<Patient> patients = patientService.findAll();
        assertNotNull(patients);
    }

    @Test
    public void testFindPatientById() {
        assertNull(patientService.findById("64abc111xyz222"));
    }

    @Test
    public void testDashboardStats() {
        assertNotNull(patientService.getDashboardStats());
    }
}
