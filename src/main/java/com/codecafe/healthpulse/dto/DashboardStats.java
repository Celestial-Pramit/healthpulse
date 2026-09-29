package com.codecafe.healthpulse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardStats {
    private long activeInPatients;          // status != DISCHARGED
    private long icuPatients;               // status == ICU
    private long operatingTheaterPatients;  // status == IN_SURGERY
    private long dischargedPatients;        // status == DISCHARGED
}
