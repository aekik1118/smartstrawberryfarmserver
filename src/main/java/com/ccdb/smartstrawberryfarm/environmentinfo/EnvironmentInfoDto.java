package com.ccdb.smartstrawberryfarm.environmentinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnvironmentInfoDto {
    @NotEmpty
    private String farmname;
    @Min(0) @Max(100)
    private double humidity;
    @NotNull
    private double temperature;
    @NotNull
    private double brightness;
}
