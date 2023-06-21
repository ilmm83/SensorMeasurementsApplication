package com.backend.controller;

import com.backend.dto.MeasurementDTO;
import com.backend.exception.MeasurementErrorResponse;
import com.backend.exception.MeasurementNotCreatedException;
import com.backend.model.Measurement;
import com.backend.service.CommonService;
import com.backend.util.ErrorUtil;
import com.backend.util.MeasurementResponse;
import com.backend.util.MeasurementsValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/measurements")
public class MeasurementsController {

    private final CommonService service;
    private final ModelMapper mapper;
    private final MeasurementsValidator validator;


    @GetMapping
    public MeasurementResponse getAllMeasurements() {
        // Usually, the list of elements wrap into one Obj for response
        return new MeasurementResponse(service.findAllMeasurements().stream()
            .map(this::convertToMeasurementDTO)
            .collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity<Integer> rainyDaysCount() {
        return ResponseEntity.ok(service.rainyDaysCount());
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurements(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            ErrorUtil.returnErrors(bindingResult);
        validator.validate(measurementDTO, bindingResult);

        service.saveMeasurement(convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> measurementNotCreatedExceptionHandler(MeasurementNotCreatedException e) {
        MeasurementErrorResponse response =
            new MeasurementErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return mapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return mapper.map(measurement, MeasurementDTO.class);
    }
}
