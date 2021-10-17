package com.analytics.iot.config;

import com.analytics.iot.model.SensorEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CustomDeserializer implements Deserializer<SensorEvent> {
    private static final Logger logger = LoggerFactory.getLogger(CustomDeserializer.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public SensorEvent deserialize(String topic, byte[] data) {
        objectMapper.registerModule(new JSR310Module());
        objectMapper.findAndRegisterModules();
        try {
            if (data == null){
                logger.warn("Null received as data in deserialize");
                return null;
            }
            logger.info("Deserializing...");
            return objectMapper.readValue(new String(data, "UTF-8"), SensorEvent.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to SensorEvent");
        }
    }

    @Override
    public void close() {
    }
}
