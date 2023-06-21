package com.backend.util;

import com.backend.dto.SensorDTO;
import com.backend.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SensorValidator implements Validator {

    private final CommonService service;


    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensor = (SensorDTO) target;      // you should not use DTO here

        if (service.sensorIsExist(sensor.getName())) {
            errors.rejectValue("name", "400", "Sensor is already exist");
        }
    }
}
