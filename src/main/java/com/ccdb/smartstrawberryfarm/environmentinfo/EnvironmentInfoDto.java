package com.ccdb.smartstrawberryfarm.environmentinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnvironmentInfoDto {
    @NotEmpty
    private String farmname;
    @NotNull
    private double humidity;
    @NotNull
    private double temperature;
    @NotNull
    private double brightness;
}
