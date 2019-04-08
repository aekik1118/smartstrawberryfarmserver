package com.ccdb.smartstrawberryfarm.environmentinfo;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
public class EnvironmentInfo {
    private Long id;
    private String farmname;
    private double humidity;
    private double temperature;
    private double brightness;
    private LocalDateTime date_prod;
}
