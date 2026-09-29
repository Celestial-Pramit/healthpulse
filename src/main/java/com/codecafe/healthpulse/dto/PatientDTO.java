package com.codecafe.healthpulse.dto;

import com.codecafe.healthpulse.enums.Acuity;
import com.codecafe.healthpulse.enums.ClinicalStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    @NotBlank
    private String mrn;
    @NotBlank
    private String patientName;
    @NotBlank
    private String department;
    @NotBlank
    private String assignedBed;
    @NotNull
    private Acuity acuity;
    private ClinicalStatus status;
}
