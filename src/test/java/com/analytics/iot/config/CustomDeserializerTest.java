package com.analytics.iot.config;

import com.analytics.iot.model.SensorEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@ExtendWith(MockitoExtension.class)
public class CustomDeserializerTest {

    @InjectMocks
    private CustomDeserializer customDeserializer = new CustomDeserializer();

    @Test
    public void testDeserialize() {
        String input = "{\"sensorPkey\":1,\"sensorId\":1,\"sensorValue\":10,\"eventTimestamp\":\"2021-12-10T12:22:22+00:00\",\"sensorType\":\"Thermistor\",\"sensorName\":\"TH1\",\"clustorId\":1}";
        SensorEvent result = customDeserializer.deserialize("iot-data",input.getBytes(StandardCharsets.UTF_8));

        Assertions.assertEquals(new BigDecimal(10),result.getSensorValue() );
        Assertions.assertEquals(1,result.getSensorId() );
        Assertions.assertEquals("Thermistor",result.getSensorType());

    }
}
