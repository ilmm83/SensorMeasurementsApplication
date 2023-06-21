package com.backend.repository;

import com.backend.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    Boolean existsByName(String name);

    Optional<Sensor> findByName(String name);
}
