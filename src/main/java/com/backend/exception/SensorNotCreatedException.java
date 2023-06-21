package com.backend.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SensorNotCreatedException extends RuntimeException{

    public SensorNotCreatedException(String message) {
        super(message);
    }
}
