package com.analytics.iot.service;

import com.analytics.iot.model.SensorEvent;
import com.analytics.iot.repository.SensorEventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SensorEventServiceTest {

    @InjectMocks
    private SensorEventService sensorEventService = new SensorEventService();
    @Mock
    private SensorEventRepository sensorEventRepository;

    @Test
    public void testGetAverageDataForSensor(){
        Mockito.when(sensorEventRepository.findBySensorId(1)).thenReturn(getSensorEventData());
        BigDecimal result = sensorEventService.getAverageDataForSensor(1);
        Assertions.assertEquals(new BigDecimal(20), result);
    }

    @Test
    public void testGetMedianDataForSensorWithOddData(){
        Mockito.when(sensorEventRepository.findBySensorId(1)).thenReturn(getSensorEventData());
        BigDecimal result = sensorEventService.getMedianDataForSensor(1);
        Assertions.assertEquals(new BigDecimal(20), result);
    }

    @Test
    public void testGetMedianDataForSensorWithEvenData(){
        Mockito.when(sensorEventRepository.findBySensorId(1)).thenReturn(getSensorEventDataWithEvenData());
        BigDecimal result = sensorEventService.getMedianDataForSensor(1);
        Assertions.assertEquals(new BigDecimal(25), result);
    }

    @Test
    public void testGetMinDataForSensor(){
        Mockito.when(sensorEventRepository.findBySensorId(1)).thenReturn(getSensorEventData());
        BigDecimal result = sensorEventService.getMinDataForSensor(1);
        Assertions.assertEquals(new BigDecimal(10), result);
    }


    @Test
    public void testGetMaxDataForSensor(){
        Mockito.when(sensorEventRepository.findBySensorId(1)).thenReturn(getSensorEventData());
        BigDecimal result = sensorEventService.getMaxDataForSensor(1);
        Assertions.assertEquals(new BigDecimal(30), result);
    }

    public List<SensorEvent> getSensorEventData() {

        SensorEvent event1 = new SensorEvent(1L, 1, new BigDecimal(10),OffsetDateTime.now(),
                "Thermister" , "TH1", 1 );
        SensorEvent event2 = new SensorEvent(2L, 1, new BigDecimal(20),OffsetDateTime.now(),
                "Thermister" , "TH1", 1 );
        SensorEvent event3 = new SensorEvent(3L, 1, new BigDecimal(30),OffsetDateTime.now(),
                "Thermister" , "TH1", 1 );

        return Arrays.asList(event1, event2, event3);
    }

    public List<SensorEvent> getSensorEventDataWithEvenData() {

        SensorEvent event1 = new SensorEvent(1L, 1, new BigDecimal(10),OffsetDateTime.now(),
                "Thermister" , "TH1", 1 );
        SensorEvent event2 = new SensorEvent(2L, 1, new BigDecimal(20),OffsetDateTime.now(),
                "Thermister" , "TH1", 1 );
        SensorEvent event3 = new SensorEvent(3L, 1, new BigDecimal(30),OffsetDateTime.now(),
                "Thermister" , "TH1", 1 );
        SensorEvent event4 = new SensorEvent(4L, 1, new BigDecimal(60),OffsetDateTime.now(),
                "Thermister" , "TH1", 1 );
        return Arrays.asList(event1, event2, event3,event4);
    }


}
