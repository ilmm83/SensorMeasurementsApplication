package com.backend.controller;

import com.backend.dto.SensorDTO;
import com.backend.exception.SensorErrorResponse;
import com.backend.exception.SensorNotCreatedException;
import com.backend.model.Sensor;
import com.backend.service.CommonService;
import com.backend.util.ErrorUtil;
import com.backend.util.SensorValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sensors")
public class SensorController {

    private final SensorValidator validator;
    private final CommonService service;
    private final ModelMapper mapper;


    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> newSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            ErrorUtil.returnErrors(bindingResult);

        validator.validate(sensorDTO, bindingResult);
        service.saveSensor(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> sensorNotCreatedExceptionHandler(SensorNotCreatedException e) {
        SensorErrorResponse response =
            new SensorErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return mapper.map(sensorDTO, Sensor.class);
    }
}
