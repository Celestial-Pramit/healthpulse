package com.codecafe.healthpulse.model;

import com.codecafe.healthpulse.enums.Acuity;
import com.codecafe.healthpulse.enums.ClinicalStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "patients")
public class Patient {
    @Id
    private String id;
    @NotBlank
    @Field("mrn")
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
    @Version                    // ← lock version column (v0, v1...) handled by Spring Data
    private Long version;
}
