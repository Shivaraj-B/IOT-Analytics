package com.analytics.iot.config;

import com.analytics.iot.model.SensorEvent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomSerializerTest {

    private CustomSerializer customSerializer;

    @Before
    public void init() {
        customSerializer = new CustomSerializer();
    }

    @Test
    public void testSerialize() throws UnsupportedEncodingException {
        String expectedResult = "{\"sensorPkey\":1,\"sensorId\":1,\"sensorValue\":10,\"eventTimestamp\":\"2021-12-10T12:22:22+00:00\",\"sensorType\":\"Thermistor\",\"sensorName\":\"TH1\",\"clustorId\":1}";
        SensorEvent event = new SensorEvent(1L, 1, new BigDecimal(10), OffsetDateTime.of(2021,12,10,12,22,22,00, ZoneOffset.UTC),
                "Thermistor" , "TH1", 1 );
        byte [] result = customSerializer.serialize("iot-data",event);

        String stringResult = new String(result, "UTF-8");
        System.out.println(stringResult);
        Assert.assertEquals(expectedResult,stringResult );

    }
}
