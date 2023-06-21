package com.backend.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MeasurementNotCreatedException extends RuntimeException{
    public MeasurementNotCreatedException(String message) {
        super(message);
    }
}
