package com.backend.util;

import com.backend.dto.MeasurementDTO;
import com.backend.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class MeasurementsValidator implements Validator {

    private final CommonService service;


    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;

        if (measurementDTO.getSensor() == null) return;

        if (!service.sensorIsExist(measurementDTO.getSensor().getName()))
            errors.rejectValue("sensor", "Sensor with this name does not exist");
    }
}
