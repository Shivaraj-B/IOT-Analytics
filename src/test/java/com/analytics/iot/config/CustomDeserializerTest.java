package com.analytics.iot.config;

import com.analytics.iot.model.SensorEvent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomDeserializerTest {

    private CustomDeserializer customDeserializer;

    @Before
    public void init() {
        customDeserializer = new CustomDeserializer();
    }

    @Test
    public void testDeserialize() {
        String input = "{\"sensorPkey\":1,\"sensorId\":1,\"sensorValue\":10,\"eventTimestamp\":\"2021-12-10T12:22:22+00:00\",\"sensorType\":\"Thermistor\",\"sensorName\":\"TH1\",\"clustorId\":1}";
        SensorEvent result = customDeserializer.deserialize("iot-data",input.getBytes(StandardCharsets.UTF_8));

        Assert.assertEquals(new BigDecimal(10),result.getSensorValue() );
        Assert.assertEquals(1,result.getSensorId() );
        Assert.assertEquals("Thermistor",result.getSensorType());

    }
}
