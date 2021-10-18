package com.analytics.iot.service;

import com.analytics.iot.model.SensorEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public final class KafkaProducerService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, SensorEvent> kafkaTemplate;
    private final String TOPIC= "iot-data";

    public KafkaProducerService(KafkaTemplate<String, SensorEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(SensorEvent message) {
        logger.info(String.format("$$$$ => Producing generic message: %s", message));
        this.kafkaTemplate.send(TOPIC, message);

    }
}