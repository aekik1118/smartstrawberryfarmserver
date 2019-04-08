package com.ccdb.smartstrawberryfarm.environmentinfo;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class EnvironmentInfoValidator {
    public void validate(EnvironmentInfoDto environmentInfoDto, Errors errors){
        if (environmentInfoDto.getHumidity() < 0 || environmentInfoDto.getHumidity() > 100){
            errors.rejectValue("humidity","wrongValue", "humidity is wrong");
        }
    }
}
