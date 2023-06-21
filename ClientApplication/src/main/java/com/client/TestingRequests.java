package com.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TestingRequests {

    public static void main(String[] args) {
        final String sensorName = "Test Name";
        Random random = new Random();

        for (int i = 0; i < 500; i++) {
            System.out.println(i);
            sendMeasurements(random.nextDouble(-100, 100), random.nextBoolean(), sensorName);
        }
    }

    private static void sendMeasurements(double value, boolean raining, String sensorName) {
        final String postURL = "http://localhost:8080/measurements/add";

        Map<String, Object> json = new HashMap<>();
        json.put("value", value);
        json.put("raining", raining);
        json.put("sensor", Map.of("name", sensorName));

        makePostRequestJSONData(postURL, json);
    }

    private static void makePostRequestJSONData(String url, Map<String, Object> json) {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(json, headers);
        restTemplate.postForObject(url, request, String.class);
    }
}
