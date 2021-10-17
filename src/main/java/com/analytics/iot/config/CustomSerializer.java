package com.analytics.iot.config;

import com.analytics.iot.model.SensorEvent;
import com.analytics.iot.service.KafkaProducerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CustomSerializer implements Serializer<SensorEvent> {
    private static final Logger logger = LoggerFactory.getLogger(CustomSerializer.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, SensorEvent data) {
        objectMapper.registerModule(new JSR310Module());
        objectMapper.findAndRegisterModules();
        try {
            if (data == null){
                logger.warn("Null received as data");
                return null;
            }
            logger.info("Serializing...");
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            logger.error("stack trace:" + e);
            throw new SerializationException("Error when serializing SensorEvent to byte[]");
        }
    }

    @Override
    public void close() {
    }
}