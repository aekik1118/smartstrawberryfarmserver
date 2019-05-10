package com.ccdb.smartstrawberryfarm.pollution;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = {"farmname", "area"})
public class Pollution {
    private String farmname;
    private String area;
    private boolean isPollution;
    private LocalDateTime date_prod;
}
