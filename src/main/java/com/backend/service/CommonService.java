package com.backend.service;

import com.backend.model.Measurement;
import com.backend.model.Sensor;
import com.backend.repository.MeasurementRepository;
import com.backend.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommonService {

    private final SensorRepository sensorRepository;
    private final MeasurementRepository measurementRepository;


    public boolean sensorIsExist(String name) {
        return sensorRepository.existsByName(name);
    }

    @Transactional
    public void saveSensor(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    @Transactional
    public void saveMeasurement(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    public List<Measurement> findAllMeasurements() {
        return measurementRepository.findAll();
    }

    public Integer rainyDaysCount() {
        int count = 0;
        for (Measurement day : measurementRepository.findAll())
           if (day.getRaining()) ++count;
        return count;
    }

    private void enrichMeasurement(Measurement measurement) {
        // we should put sensor obj from persistent context to avoid exception
        measurement.setSensor(sensorRepository.findByName(measurement.getSensor().getName()).get());
        measurement.setCreated_at(LocalDateTime.now());
    }
}
