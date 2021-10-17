package com.analytics.iot.controller;

import com.analytics.iot.model.SensorEvent;
import com.analytics.iot.service.KafkaProducerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public final class KafkaController {
    private final KafkaProducerService producerService;

    public KafkaController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }


    @PostMapping("/publish")
    public ResponseEntity<?> ndMessageToKafkaTopic(@RequestBody SensorEvent message) {
        producerService.sendMessage(message);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }
}
