package com.backend.util;

import com.backend.exception.SensorNotCreatedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ErrorUtil {
    public static void returnErrors(BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder();

        for (FieldError field : bindingResult.getFieldErrors()) {
            sb.append(field.getField()).append(" - ").append(field.getDefaultMessage()).append(";");
        }

        throw new SensorNotCreatedException(sb.toString());
    }
}
