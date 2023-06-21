package com.backend.util;

import com.backend.dto.MeasurementDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class MeasurementResponse {
    private List<MeasurementDTO> measurements;
}
