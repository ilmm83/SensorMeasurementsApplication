package com.client;

import com.client.json_parser.MeasurementParser;
import com.client.json_parser.MeasurementResponse;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DrawChart {

    public static void main(String[] args) {
        List<Double> temp = makeAGraph();
        drawChart(temp);
    }


    private static List<Double> makeAGraph() {
        final RestTemplate template = new RestTemplate();
        final String url = "http://localhost:8080/measurements";

        MeasurementResponse response = template.getForObject(url, MeasurementResponse.class);
        if (response == null || response.getList() == null) return Collections.emptyList();

        return response.getList().stream()
                .map(MeasurementParser::getValue)
                .collect(Collectors.toList());
    }

    private static void drawChart(List<Double> list) {
        double[] xData = IntStream.range(0, list.size()).asDoubleStream().toArray();
        double[] yData = list.stream().mapToDouble(x -> x).toArray();

        XYChart chart = QuickChart.getChart("Temperature", "X", "Y", "Temp", xData, yData);

        new SwingWrapper<>(chart).displayChart();
    }
}
