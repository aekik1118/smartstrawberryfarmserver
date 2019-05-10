package com.ccdb.smartstrawberryfarm.pollution;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class PollutionDto {
    @NotEmpty
    private String farmname;
    @NotEmpty
    private String area;
    private boolean isPollution;
}
