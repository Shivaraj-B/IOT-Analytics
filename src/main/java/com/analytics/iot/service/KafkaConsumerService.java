package com.analytics.iot.service;

import com.analytics.iot.model.SensorEvent;
import com.analytics.iot.repository.SensorEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public final class KafkaConsumerService {

    @Autowired
    private SensorEventRepository repo;
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    //@KafkaListener(topics = "${kafka.topic}", groupId = "group_id")
    @KafkaListener(topics = "iot-data", groupId = "group_id" )
    public void consume(SensorEvent message) {
        logger.info(String.format("$$$$ => Consumed genric message at: %s", message.getEventTimestamp().toString()));
        repo.save(message);
    }
}
