package com.client.json_parser;

import lombok.Data;

import java.util.List;

@Data
public class MeasurementResponse {
    private List<MeasurementParser> list;
}
