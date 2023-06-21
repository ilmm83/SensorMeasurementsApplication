package com.client.json_parser;

import lombok.Data;

@Data
public class MeasurementParser {
    private Double value;
    private Boolean raining;
    private SensorParser sensor;
}
