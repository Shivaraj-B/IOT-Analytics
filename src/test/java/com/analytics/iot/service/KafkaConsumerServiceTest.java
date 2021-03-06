package com.analytics.iot.service;

import com.analytics.iot.model.SensorEvent;
import com.analytics.iot.repository.SensorEventRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class KafkaConsumerServiceTest {

    private KafkaConsumerService service;

    @MockBean
    private SensorEventRepository sensorEventRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        service = new KafkaConsumerService(sensorEventRepository);
    }


    @Test
    public void testKafkaConsumer(){
        SensorEvent event = new SensorEvent(1L, 1, new BigDecimal(10), OffsetDateTime.now(),
                "Thermister" , "TH1", 1 );
        Mockito.when(sensorEventRepository.save(event)).thenReturn(event);
        service.consume(event);
        verify(sensorEventRepository, times(1)).save(event);
    }

}
