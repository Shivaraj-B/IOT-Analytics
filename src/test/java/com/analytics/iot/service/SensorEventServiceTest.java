package com.analytics.iot.service;

import com.analytics.iot.model.SensorEvent;
import com.analytics.iot.repository.SensorEventRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class SensorEventServiceTest {

    @MockBean
    private SensorEventRepository sensorEventRepository;

    private SensorEventService sensorEventService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        
        sensorEventService = new SensorEventService(sensorEventRepository);
        when(sensorEventRepository.findBySensorId(1)).thenReturn(getSensorEventData());
    }

    @Test
    public void testGetAverageDataForSensor(){
       // when(sensorEventRepository.findBySensorId(1)).thenReturn(getSensorEventData());
        BigDecimal result = sensorEventService.getAverageDataForSensor(1);
        Assert.assertEquals(new BigDecimal(20), result);
    }

    @Test
    public void testGetMedianDataForSensorWithOddData(){
       //when(sensorEventRepository.findBySensorId(1)).thenReturn(getSensorEventData());
        BigDecimal result = sensorEventService.getMedianDataForSensor(1);
        Assert.assertEquals(new BigDecimal(20), result);
    }

    @Test
    public void testGetMedianDataForSensorWithEvenData(){
        when(sensorEventRepository.findBySensorId(1)).thenReturn(getSensorEventDataWithEvenData());
        BigDecimal result = sensorEventService.getMedianDataForSensor(1);
        Assert.assertEquals(new BigDecimal(25), result);
    }


    @Test
    public void testGetMinDataForSensor(){
        //when(sensorEventRepository.findBySensorId(1)).thenReturn(getSensorEventData());
        BigDecimal result = sensorEventService.getMinDataForSensor(1);
        Assert.assertEquals(new BigDecimal(10), result);
    }


    @Test
    public void testGetMaxDataForSensor(){
        //when(sensorEventRepository.findBySensorId(1)).thenReturn(getSensorEventData());
        BigDecimal result = sensorEventService.getMaxDataForSensor(1);
        Assert.assertEquals(new BigDecimal(30), result);
    }

    @Test
    public void testGetMedianDataForSensorWithNull(){
        when(sensorEventRepository.findBySensorId(1)).thenReturn(null);
        BigDecimal result = sensorEventService.getMedianDataForSensor(1);
        Assert.assertNull(result);
    }

    @Test
    public void testGetAverageDataForSensorWithNull(){
        when(sensorEventRepository.findBySensorId(1)).thenReturn(null);
        BigDecimal result = sensorEventService.getAverageDataForSensor(1);
        Assert.assertNull(result);
    }

    @Test
    public void testGetMinDataForSensorWithNull(){
        when(sensorEventRepository.findBySensorId(1)).thenReturn(null);
        BigDecimal result = sensorEventService.getMinDataForSensor(1);
        Assert.assertNull(result);
    }

    @Test
    public void testGetMaxDataForSensorWithNull(){
        when(sensorEventRepository.findBySensorId(1)).thenReturn(null);
        BigDecimal result = sensorEventService.getMaxDataForSensor(1);
        Assert.assertNull(result);
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
