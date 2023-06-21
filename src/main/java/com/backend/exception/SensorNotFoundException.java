package com.backend.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SensorNotFoundException extends RuntimeException {
    public SensorNotFoundException(String message) {
        super(message);
    }
}
